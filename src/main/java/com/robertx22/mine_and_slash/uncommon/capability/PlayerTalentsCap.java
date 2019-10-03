package com.robertx22.mine_and_slash.uncommon.capability;

import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.new_content_test.talent_tree.TalentConnection;
import com.robertx22.mine_and_slash.new_content_test.talent_tree.TalentPoint;
import com.robertx22.mine_and_slash.saveclasses.talents.PlayerTalentsData;
import com.robertx22.mine_and_slash.uncommon.capability.bases.BaseProvider;
import com.robertx22.mine_and_slash.uncommon.capability.bases.BaseStorage;
import com.robertx22.mine_and_slash.uncommon.capability.bases.ICommonCapability;
import com.robertx22.mine_and_slash.uncommon.datasaving.base.LoadSave;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashSet;
import java.util.Set;

@Mod.EventBusSubscriber
public class PlayerTalentsCap {

    public static final ResourceLocation RESOURCE = new ResourceLocation(Ref.MODID, "player_talents");

    private static final String LOC = "PLAYER_TALENTS_DATA";

    @CapabilityInject(IPlayerTalentsData.class)
    public static final Capability<IPlayerTalentsData> Data = null;

    public interface IPlayerTalentsData extends ICommonCapability {
        Set<TalentConnection> getConnections();
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

    public static class Provider extends BaseProvider<IPlayerTalentsData> {

        @Override
        public IPlayerTalentsData defaultImpl() {
            return new DefaultImpl();
        }

        @Override
        public Capability<IPlayerTalentsData> dataInstance() {
            return Data;
        }
    }

    public static class DefaultImpl implements IPlayerTalentsData {

        PlayerTalentsData data = new PlayerTalentsData();

        @Override
        public CompoundNBT getNBT() {
            CompoundNBT nbt = new CompoundNBT();
            LoadSave.Save(data, nbt, LOC);
            return nbt;
        }

        @Override
        public void setNBT(CompoundNBT nbt) {
            this.data = LoadSave.Load(PlayerTalentsData.class, new PlayerTalentsData(), nbt, LOC);
        }

        @Override
        public Set<TalentConnection> getConnections() {

            HashSet<TalentConnection> set = new HashSet<>();

            for (TalentPoint talent : SlashRegistry.Talents().getList()) {

                TalentConnection.Allocation status = TalentConnection.Allocation.CANT_ALLOCATE;

                if (this.data.isAllocated(talent.GUID())) {
                    status = TalentConnection.Allocation.ALLOCATED;
                } else {
                    for (TalentPoint con : talent.connections) {
                        if (this.data.isAllocated(con.GUID())) {
                            status = TalentConnection.Allocation.CAN_ALLOCATE;
                        }
                    }

                }

                TalentConnection tc = new TalentConnection();

            }

            return set;

        }
    }

    public static class Storage extends BaseStorage<IPlayerTalentsData> {

    }

}
