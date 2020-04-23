package com.robertx22.mine_and_slash.uncommon.capability.world;

import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.saveclasses.dungeon_dimension.DungeonDimensionData;
import com.robertx22.mine_and_slash.uncommon.capability.bases.BaseProvider;
import com.robertx22.mine_and_slash.uncommon.capability.bases.BaseStorage;
import com.robertx22.mine_and_slash.uncommon.capability.bases.ICommonCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.base.LoadSave;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class AntiMobFarmCap {

    public static final ResourceLocation RESOURCE = new ResourceLocation(Ref.MODID, "anti_mob_farm");

    @CapabilityInject(IAntiMobFarmData.class)
    public static final Capability<IAntiMobFarmData> Data = null;

    public interface IAntiMobFarmData extends ICommonCap {

        void onValidMobDeathByPlayer(LivingEntity en);

        float getDropMultiForMob(LivingEntity en);

    }

    @Mod.EventBusSubscriber
    public static class EventHandler {

        @SubscribeEvent
        public static void onEntityConstruct(AttachCapabilitiesEvent<World> event) {
            event.addCapability(RESOURCE, new Provider());
        }

    }

    public static class Provider extends BaseProvider<IAntiMobFarmData> {

        @Override
        public IAntiMobFarmData defaultImpl() {
            return new DefaultImpl();
        }

        @Override
        public Capability<IAntiMobFarmData> dataInstance() {
            return Data;
        }
    }

    static String DATA_LOC = Ref.MODID + ":data";

    public static class DefaultImpl implements IAntiMobFarmData {

        DungeonDimensionData data = new DungeonDimensionData();

        @Override
        public CompoundNBT saveToNBT() {

            CompoundNBT nbt = new CompoundNBT();

            if (data != null) {
                LoadSave.Save(data, nbt, DATA_LOC);
            }

            return nbt;

        }

        @Override
        public void loadFromNBT(CompoundNBT nbt) {

            data = LoadSave.Load(DungeonDimensionData.class, new DungeonDimensionData(), nbt, DATA_LOC);

            if (data == null) {
                data = new DungeonDimensionData();
            }

        }

    }

    public static class Storage extends BaseStorage<IAntiMobFarmData> {

    }

}
