package com.robertx22.mine_and_slash.uncommon.capability;

import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.saveclasses.professions.ProfessionListData;
import com.robertx22.mine_and_slash.uncommon.capability.bases.BaseProvider;
import com.robertx22.mine_and_slash.uncommon.capability.bases.BaseStorage;
import com.robertx22.mine_and_slash.uncommon.capability.bases.ICommonCapability;
import com.robertx22.mine_and_slash.uncommon.datasaving.ProfessionSaving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class PlayerStatsCap {

    public static final ResourceLocation RESOURCE = new ResourceLocation(Ref.MODID, "player_stats");

    @CapabilityInject(IPlayerStatsData.class)
    public static final Capability<IPlayerStatsData> Data = null;

    public interface IPlayerStatsData extends ICommonCapability {

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

    public static class Provider extends BaseProvider<IPlayerStatsData> {

        @Override
        public IPlayerStatsData defaultImpl() {
            return new DefaultImpl();
        }

        @Override
        public Capability<IPlayerStatsData> dataInstance() {
            return Data;
        }
    }

    public static class DefaultImpl implements IPlayerStatsData {

        ProfessionListData data = new ProfessionListData();

        @Override
        public CompoundNBT getNBT() {

            CompoundNBT nbt = new CompoundNBT();

            ProfessionSaving.Save(nbt, data);

            return nbt;

        }

        @Override
        public void setNBT(CompoundNBT nbt) {
            this.data = ProfessionSaving.Load(nbt);
        }

    }

    public static class Storage extends BaseStorage<IPlayerStatsData> {

    }

}
