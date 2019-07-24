package com.robertx22.mine_and_slash.uncommon.capability;

import com.robertx22.mine_and_slash.config.ModConfig;
import com.robertx22.mine_and_slash.dimensions.MapManager;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.saveclasses.item_classes.MapItemData;
import com.robertx22.mine_and_slash.uncommon.capability.bases.BaseProvider;
import com.robertx22.mine_and_slash.uncommon.capability.bases.BaseStorage;
import com.robertx22.mine_and_slash.uncommon.capability.bases.ICommonCapability;
import com.robertx22.mine_and_slash.uncommon.datasaving.Map;
import com.robertx22.mine_and_slash.uncommon.localization.Chats;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.PlayerUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.WorldUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.UUID;

@Mod.EventBusSubscriber
public class PlayerMapCap {

    public static final ResourceLocation RESOURCE = new ResourceLocation(Ref.MODID, "playermapdata");

    @CapabilityInject(IPlayerMapData.class)
    public static final Capability<IPlayerMapData> Data = null;

    static final String POS_OBJ = "POS_OBJ";
    static final String ORIGINAL_DIM = "original_dimension";
    static final String MAP_GUID = "MAP_GUID";
    static final String MIN_PASSED = "MIN_PASSED";

    public interface IPlayerMapData extends ICommonCapability {

        void onTickIfDead(ServerPlayerEntity player);

        float getLootMultiplier(PlayerEntity player);

        String getLastMapGUID();

        boolean hasTimeForMap();

        int getLevel();

        int getTier();

        MapItemData getMap();

        BlockPos getMapDevicePos();

        DimensionType getOriginalDimension();

        void teleportPlayerBack(PlayerEntity player);

        void onPlayerDeath(PlayerEntity player);

        boolean isPermaDeath();

        void onMinute(PlayerEntity player);

        void init(BlockPos pos, MapItemData map, DimensionType type, PlayerEntity player);

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

        long mapDevicePos;
        MapItemData mapdata = new MapItemData();
        DimensionType originalDimension = null;
        int minutesPassed = 0;
        String mapGUID = ""; // used to check if same map for chests
        boolean isDead = false;

        @Override
        public CompoundNBT getNBT() {

            CompoundNBT nbt = new CompoundNBT();

            if (mapdata != null) {
                Map.Save(nbt, mapdata);
            }

            nbt.putString(MAP_GUID, mapGUID);
            nbt.putLong(POS_OBJ, mapDevicePos);
            nbt.putInt(MIN_PASSED, minutesPassed);
            nbt.putBoolean("isdead", isDead);

            if (this.originalDimension != null) {
                nbt.putString(ORIGINAL_DIM, MapManager.getResourceLocation(originalDimension)
                        .toString());
            }

            return nbt;

        }

        @Override
        public void setNBT(CompoundNBT nbt) {

            mapdata = Map.Load(nbt);
            this.mapDevicePos = nbt.getLong(POS_OBJ);
            this.mapGUID = nbt.getString(MAP_GUID);
            this.minutesPassed = nbt.getInt(MIN_PASSED);
            this.isDead = nbt.getBoolean("isdead");

            if (nbt.contains(ORIGINAL_DIM)) {
                this.originalDimension = DimensionType.byName(new ResourceLocation(nbt.getString(ORIGINAL_DIM)));
            } else {
                this.originalDimension = DimensionType.OVERWORLD;
            }

        }

        @Override
        public void onPlayerDeath(PlayerEntity player) {

            this.isDead = true;

            if (this.isPermaDeath()) {

                this.minutesPassed += 555555;

                player.sendMessage(Chats.Player_died_in_a_map_world.locName()
                        .appendText(" " + player.getDisplayName()
                                .getFormattedText() + " ")
                        .appendSibling(Chats.Time_ran_out_due_to_Permadeath.locName()));

            } else {
                int punishment = 5;

                player.sendMessage(Chats.Player_died_in_a_map_world.locName()
                        .appendText(" " + player.getDisplayName()
                                .getFormattedText() + " ")
                        .appendSibling(Chats.Map_time_penalty_activated.locName()));

                this.minutesPassed += punishment;

                announceTimeLeft(player);
            }

            if (ModConfig.INSTANCE.Server.DISABLE_DEATH_IN_MAPS.get()) {
                player.setHealth(player.getMaxHealth()); // needs to have more hp to actually teleport lol and not die
            }

        }

        @Override
        public boolean isPermaDeath() {
            return mapdata.isPermaDeath;
        }

        @Override
        public void onMinute(PlayerEntity player) {
            this.minutesPassed++;

            if (this.getMinutesLeft() < 1) {

                this.announceEnd(player);
                this.teleportPlayerBack(player);

            } else {
                onMinutePassAnnounce(player);

            }

        }

        @Override
        public void init(BlockPos pos, MapItemData map, DimensionType type,
                         PlayerEntity player) {

            this.minutesPassed = 0;
            this.mapDevicePos = pos.toLong();
            this.originalDimension = player.world.getDimension().getType();
            this.mapdata = map;
            this.mapGUID = UUID.randomUUID().toString();

        }

        private void onMinutePassAnnounce(PlayerEntity player) {
            int minutesLeft = getMinutesLeft();

            if (minutesLeft > 0) {
                if (minutesLeft < 5 || minutesLeft % 5 == 0) {
                    announceTimeLeft(player);
                }
            }
        }

        @Override
        public void onTickIfDead(ServerPlayerEntity player) {
            if (isDead) {
                this.isDead = false;
                teleportPlayerBack(player);
            }
        }

        @Override
        public float getLootMultiplier(PlayerEntity player) {
            if (WorldUtils.isMapWorld(player.world)) {
                return this.mapdata.getBonusLootMulti();
            } else {
                return 1;
            }
        }

        @Override
        public String getLastMapGUID() {
            return this.mapGUID;
        }

        @Override
        public boolean hasTimeForMap() {
            return this.getMinutesLeft() > 0;
        }

        @Override
        public int getLevel() {
            return this.mapdata.level;
        }

        @Override
        public int getTier() {
            return this.mapdata.tier;
        }

        @Override
        public MapItemData getMap() {
            if (mapdata != null) {
                return this.mapdata;
            } else {
                return new MapItemData();
            }
        }

        @Override
        public BlockPos getMapDevicePos() {
            return BlockPos.fromLong(mapDevicePos).south(3);
        }

        @Override
        public DimensionType getOriginalDimension() {
            return originalDimension;
        }

        @Override
        public void teleportPlayerBack(PlayerEntity player) {

            if (WorldUtils.isMapWorld(player.world)) {

                if (this.originalDimension == null) {
                    this.originalDimension = DimensionType.OVERWORLD;

                }

                BlockPos pos = getMapDevicePos();

                if (pos == null) {
                    pos = player.getBedLocation();
                }
                if (pos == null) {
                    try {
                        pos = MapManager.getWorld(DimensionType.OVERWORLD)
                                .getSpawnPoint();
                    } catch (Exception e) {
                        e.printStackTrace();
                        pos = new BlockPos(0, 90, 0);
                    }
                }

                pos = pos.north(2);
                PlayerUtils.changeDimension((ServerPlayerEntity) player, this.originalDimension, pos); // TODO conditionIsMet if works

            }
        }

        private void announceEnd(PlayerEntity player) {

            player.sendMessage(Chats.Ran_Out_Of_Time.locName());

        }

        private void announceTimeLeft(PlayerEntity player) {

            player.sendMessage(Chats.Remaining_Map_Time_is.locName()
                    .appendText(" " + this.getMinutesLeft() + " ")
                    .appendSibling(Words.Minutes.locName()));

        }

        private int getMinutesLeft() {
            return this.getMap().minutes - this.minutesPassed;

        }
    }

    public static class Storage extends BaseStorage<IPlayerMapData> {

    }

}
