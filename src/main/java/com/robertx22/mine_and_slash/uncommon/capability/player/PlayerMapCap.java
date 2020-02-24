package com.robertx22.mine_and_slash.uncommon.capability.player;

import com.robertx22.mine_and_slash.config.forge.ModConfig;
import com.robertx22.mine_and_slash.dimensions.MapManager;
import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.packets.sync_cap.PlayerCaps;
import com.robertx22.mine_and_slash.saveclasses.PlayerWholeMapData;
import com.robertx22.mine_and_slash.saveclasses.item_classes.MapItemData;
import com.robertx22.mine_and_slash.uncommon.capability.bases.BaseProvider;
import com.robertx22.mine_and_slash.uncommon.capability.bases.BaseStorage;
import com.robertx22.mine_and_slash.uncommon.capability.bases.ICommonPlayerCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.base.LoadSave;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.PlayerUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.WorldUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Optional;

@Mod.EventBusSubscriber
public class PlayerMapCap {

    public static final ResourceLocation RESOURCE = new ResourceLocation(Ref.MODID, "playermapdata");

    @CapabilityInject(IPlayerMapData.class)
    public static final Capability<IPlayerMapData> Data = null;

    static final String LOC = Ref.MODID + "data";

    public interface IPlayerMapData extends ICommonPlayerCap {

        void onTickIfDead(ServerPlayerEntity player);

        float getLootMultiplier(PlayerEntity player);

        boolean isMapActive();

        String getLastMapGUID();

        MapItemData getMap();

        BlockPos getMapDevicePos(World world);

        DimensionType getOriginalDimension();

        void teleportPlayerBack(PlayerEntity player);

        void onPlayerDeath(PlayerEntity player);

        boolean isPermaDeath();

        void init(BlockPos pos, MapItemData map, DimensionType type, PlayerEntity player);

        void onQuestFinished();

        float getMapLootMultiplierForTime();

        void onMapDropped();

    }

    @Mod.EventBusSubscriber
    public static class EventHandler {

        @SubscribeEvent
        public static void onEntityConstruct(AttachCapabilitiesEvent<Entity> event) {

            if (event.getObject() instanceof PlayerEntity) {
                event.addCapability(RESOURCE, new Provider());
            }
        }

    }

    public static class Provider extends BaseProvider<IPlayerMapData> {

        @Override
        public IPlayerMapData defaultImpl() {
            return new DefaultImpl();
        }

        @Override
        public Capability<IPlayerMapData> dataInstance() {
            return Data;
        }
    }

    public static class DefaultImpl implements IPlayerMapData {

        PlayerWholeMapData data = new PlayerWholeMapData();

        @Override
        public CompoundNBT saveToNBT() {

            CompoundNBT nbt = new CompoundNBT();

            if (data != null) {
                LoadSave.Save(data, nbt, LOC);
            }

            return nbt;

        }

        @Override
        public void loadFromNBT(CompoundNBT nbt) {
            data = LoadSave.Load(PlayerWholeMapData.class, new PlayerWholeMapData(), nbt, LOC);

            if (data == null) {
                data = new PlayerWholeMapData();
            }
        }

        @Override
        public PlayerCaps getCapType() {
            return PlayerCaps.MAP_DATA;
        }

        @Override
        public void onPlayerDeath(PlayerEntity player) {

            this.data.isDead = true;

            if (ModConfig.INSTANCE.Server.DISABLE_DEATH_IN_MAPS.get()) {
                player.setHealth(player.getMaxHealth()); // needs to have more hp to actually teleport lol and not die
            }

        }

        @Override
        public boolean isPermaDeath() {
            return false;
        }

        @Override
        public void init(BlockPos pos, MapItemData map, DimensionType type, PlayerEntity player) {

            this.data = new PlayerWholeMapData();

            this.data.mapDevicePos = new BlockPos(pos).up();
            this.data.setOriginalDimension(player.world.getDimension()
                .getType());
            this.data.mapdata = map.clone();
            this.data.questFinished = false;
            this.data.setPlayerId(player);
            this.data.isActive = true;

            MMORPG.syncMapData((ServerPlayerEntity) player);
        }

        @Override
        public void onQuestFinished() {
            this.data.questFinished = true;
        }

        @Override // a self balancing map loot drop rate that isn't too much or too little.
        public float getMapLootMultiplierForTime() {
            float total = 1 + data.mapDropPoints;

            return MathHelper.clamp(total, 0.01F, 3F);
        }

        @Override
        public void onMapDropped() {
            this.data.mapDropPoints = 0;
        }

        @Override
        public void onTickIfDead(ServerPlayerEntity player) {
            if (data != null) {
                if (data.isDead) {
                    this.data.isDead = false;
                    teleportPlayerBack(player);
                }
            }
        }

        @Override
        public float getLootMultiplier(PlayerEntity player) {

            return 1;
        }

        @Override
        public boolean isMapActive() {
            return data != null && data.isActive;
        }

        @Override
        public String getLastMapGUID() {
            return this.getMap().mapUUID;
        }

        @Override
        public MapItemData getMap() {
            if (data != null && data.mapdata != null) {
                return this.data.mapdata;
            } else {
                return MapItemData.empty();
            }
        }

        @Override
        public BlockPos getMapDevicePos(World world) {

            if (data.mapDevicePos.getY() < 3) {
                data.mapDevicePos = WorldUtils.getSurface(world, data.mapDevicePos);
            }

            return data.mapDevicePos;
        }

        @Override
        public DimensionType getOriginalDimension() {
            return data.getOriginalDimension();
        }

        private static void error(String str) {
            System.out.println("[Mine and Slash Map Error]: " + str);
        }

        @Override
        public void teleportPlayerBack(PlayerEntity player) {

            if (WorldUtils.isMapWorldClass(player.world)) {

                BlockPos pos = getMapDevicePos(player.world);

                if (pos == null) {
                    error("Map device pos is null");

                    pos = player.getBedLocation();

                    if (pos == null) {
                        error("Bed location attempt:1 is null");
                    }

                    try {
                        Optional<BlockPos> opt = player.getBedPosition();
                        if (opt.isPresent()) {
                            pos = opt.get();
                        }
                    } catch (Exception e) {
                        error("Bed location attempt:2 is null");
                    }
                }
                if (pos == null) {
                    try {
                        pos = MapManager.getWorld(DimensionType.OVERWORLD)
                            .getSpawnPoint();
                    } catch (Exception e) {
                        error("Last safeguard failed, can't even get spawn point of overworld");
                        e.printStackTrace();
                        pos = new BlockPos(0, 90, 0);
                    }
                }

                // pos = pos.north(2);
                PlayerUtils.changeDimension((ServerPlayerEntity) player, data.getOriginalDimension(), pos);

            }
        }

    }

    public static class Storage extends BaseStorage<IPlayerMapData> {

    }

}
