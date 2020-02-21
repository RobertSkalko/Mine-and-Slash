package com.robertx22.mine_and_slash.uncommon.capability.world;

import com.robertx22.mine_and_slash.database.map_events.base.MapEvent;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.saveclasses.MapEventsData;
import com.robertx22.mine_and_slash.saveclasses.item_classes.MapItemData;
import com.robertx22.mine_and_slash.uncommon.capability.bases.BaseProvider;
import com.robertx22.mine_and_slash.uncommon.capability.bases.BaseStorage;
import com.robertx22.mine_and_slash.uncommon.capability.bases.ICommonCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Map;
import com.robertx22.mine_and_slash.uncommon.datasaving.base.LoadSave;
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

        float getLootMultiplier();

        float getExpMultiplier();

        int getLevel();

        int getTier();

        MapItemData getMap();

        boolean isPermaDeath();

        void init(MapItemData map);

        void startRandomMapEvent(World world);

        void startEvent(MapEvent event, World world);

        MapEventsData getEvents();
    }

    @Mod.EventBusSubscriber
    public static class EventHandler {

        @SubscribeEvent
        public static void onEntityConstruct(AttachCapabilitiesEvent<World> event) {
            event.addCapability(RESOURCE, new Provider());
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

    static String EVENTS_LOC = Ref.MODID + ":events";

    public static class DefaultImpl implements IWorldMapData {

        MapItemData mapdata = null;
        MapEventsData events = new MapEventsData();

        @Override
        public CompoundNBT saveToNBT() {

            CompoundNBT nbt = new CompoundNBT();

            if (mapdata != null) {
                Map.Save(nbt, mapdata);
            }

            if (events != null) {
                LoadSave.Save(events, nbt, EVENTS_LOC);
            }

            return nbt;

        }

        @Override
        public void loadFromNBT(CompoundNBT nbt) {
            mapdata = Map.Load(nbt);
            events = LoadSave.Load(MapEventsData.class, new MapEventsData(), nbt, EVENTS_LOC);

            if (events == null) {
                events = new MapEventsData();
            }
        }

        @Override
        public boolean isPermaDeath() {
            return mapdata.isPermaDeath;
        }

        @Override
        public void init(MapItemData map) {
            if (mapdata == null) { // only init once, after that its set in stone
                this.mapdata = map.clone();
            }
        }

        @Override
        public void startRandomMapEvent(World world) {
            this.events.add(SlashRegistry.MapEvents()
                .getFilterWrapped(x -> !events.isActive(x))
                .random(), world);
        }

        @Override
        public void startEvent(MapEvent event, World world) {
            this.events.add(event, world);

        }

        @Override
        public MapEventsData getEvents() {
            if (events == null) {
                events = new MapEventsData();
            }

            return events;
        }

        @Override
        public float getLootMultiplier() {

            return this.getMap()
                .getBonusLootMulti();

        }

        @Override
        public float getExpMultiplier() {
            return 1 + getMap().tier * 0.05F;
        }

        @Override
        public int getLevel() {
            return this.getMap().level;
        }

        @Override
        public int getTier() {
            return this.getMap().tier;
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
