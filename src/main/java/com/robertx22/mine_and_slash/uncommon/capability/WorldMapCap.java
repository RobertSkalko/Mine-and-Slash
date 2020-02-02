package com.robertx22.mine_and_slash.uncommon.capability;

import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.saveclasses.item_classes.MapItemData;
import com.robertx22.mine_and_slash.uncommon.capability.bases.BaseProvider;
import com.robertx22.mine_and_slash.uncommon.capability.bases.BaseStorage;
import com.robertx22.mine_and_slash.uncommon.capability.bases.ICommonCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Map;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class WorldMapCap {

    public static final ResourceLocation RESOURCE = new ResourceLocation(Ref.MODID, "adventure_map");

    @CapabilityInject(IWorldMapData.class)
    public static final Capability<IWorldMapData> Data = null;

    public interface IWorldMapData extends ICommonCap {

        float getLootMultiplier(PlayerEntity player);

        float getExpMultiplier();

        int getLevel();

        int getTier();

        MapItemData getMap();

        boolean isPermaDeath();

        void init(MapItemData map);

    }

    @Mod.EventBusSubscriber
    public static class EventHandler {

        @SubscribeEvent
        public static void onEntityConstruct(AttachCapabilitiesEvent<World> event) {

            if (event.getObject() instanceof World) {
                event.addCapability(RESOURCE, new Provider());
            }
        }

    }

    public static class Provider extends BaseProvider<IWorldMapData> {

        @Override
        public IWorldMapData defaultImpl() {
            return new DefaultImpl();
        }

        @Override
        public Capability<IWorldMapData> dataInstance() {
            return Data;
        }
    }

    public static class DefaultImpl implements IWorldMapData {

        MapItemData mapdata = new MapItemData();

        @Override
        public CompoundNBT getNBT() {

            CompoundNBT nbt = new CompoundNBT();

            if (mapdata != null) {
                Map.Save(nbt, mapdata);
            }

            return nbt;

        }

        @Override
        public void setNBT(CompoundNBT nbt) {

            mapdata = Map.Load(nbt);

        }

        @Override
        public boolean isPermaDeath() {
            return mapdata.isPermaDeath;
        }

        @Override
        public void init(MapItemData map) {

            this.mapdata = map.clone();

        }

        @Override
        public float getLootMultiplier(PlayerEntity player) {

            return this.getMap().getBonusLootMulti();

        }

        @Override
        public float getExpMultiplier() {
            return 1 + getMap().tier * 0.05F;
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
                return MapItemData.empty();
            }
        }

    }

    public static class Storage extends BaseStorage<IWorldMapData> {

    }

}
