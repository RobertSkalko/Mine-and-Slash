package com.robertx22.mine_and_slash.uncommon.capability.server_wide;

import com.robertx22.mine_and_slash.mmorpg.Ref;
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
public class TeamCap {

    public static final ResourceLocation RESOURCE = new ResourceLocation(Ref.MODID, "teams");

    @CapabilityInject(ITeamData.class)
    public static final Capability<ITeamData> Data = null;

    public interface ITeamData extends ICommonCap {

    }

    @Mod.EventBusSubscriber
    public static class EventHandler {
        @SubscribeEvent
        public static void onEntityConstruct(AttachCapabilitiesEvent<World> event) {
            event.addCapability(RESOURCE, new Provider());
        }
    }

    public static class Provider extends BaseProvider<ITeamData> {

        @Override
        public ITeamData defaultImpl() {
            return new DefaultImpl();
        }

        @Override
        public Capability<ITeamData> dataInstance() {
            return Data;
        }
    }

    static String DATA_LOC = Ref.MODID + ":data";

    public static class DefaultImpl implements ITeamData {

        MapItemData mapdata = null;
        MapEventsData events = new MapEventsData();

        @Override
        public CompoundNBT saveToNBT() {

            CompoundNBT nbt = new CompoundNBT();

            if (mapdata != null) {
                Map.Save(nbt, mapdata);
            }

            if (events != null) {
                LoadSave.Save(events, nbt, DATA_LOC);
            }

            return nbt;

        }

        @Override
        public void loadFromNBT(CompoundNBT nbt) {
            mapdata = Map.Load(nbt);
            events = LoadSave.Load(MapEventsData.class, new MapEventsData(), nbt, DATA_LOC);

            if (events == null) {
                events = new MapEventsData();
            }
        }

    }

    public static class Storage extends BaseStorage<ITeamData> {

    }

}
