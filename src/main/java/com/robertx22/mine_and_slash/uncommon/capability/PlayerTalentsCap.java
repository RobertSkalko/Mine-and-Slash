package com.robertx22.mine_and_slash.uncommon.capability;

import com.robertx22.mine_and_slash.config.ModConfig;
import com.robertx22.mine_and_slash.database.talent_tree.Perk;
import com.robertx22.mine_and_slash.database.talent_tree.PerkConnection;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.mmorpg.Ref;
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

import static com.robertx22.mine_and_slash.database.talent_tree.PerkConnection.Allocation.*;

@Mod.EventBusSubscriber
public class PlayerTalentsCap {

    public static final ResourceLocation RESOURCE = new ResourceLocation(Ref.MODID, "player_talents");

    private static final String LOC = "PLAYER_TALENTS_DATA";

    @CapabilityInject(IPlayerTalentsData.class)
    public static final Capability<IPlayerTalentsData> Data = null;

    public interface IPlayerTalentsData extends ICommonCapability {
        Set<PerkConnection> getConnections();

        boolean canAllocatePoint(Perk talent, EntityCap.UnitData data);

        boolean hasPerk(Perk perk);

        boolean tryRemovePoint(Perk talent);

        void allocate(Perk talent);

        int getFreePoints(EntityCap.UnitData data);

        int getAllocatedPoints();

        void reset();

        void applyStats(EntityCap.UnitData data, PlayerEntity player);

        PlayerTalentsData getData();
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

            if (data == null) {
                data = new PlayerTalentsData();
            }
        }

        @Override
        public Set<PerkConnection> getConnections() {

            HashSet<PerkConnection> set = new HashSet<>();

            for (Perk talent : SlashRegistry.Perks().getList()) {

                if (data.isAllocated(talent)) {
                    for (Perk con : talent.connections) {
                        if (data.isAllocated(con)) {
                            set.add(new PerkConnection(ALLOCATED, talent, con));
                        } else {
                            set.add(new PerkConnection(CAN_ALLOCATE, talent, con));
                        }
                    }

                } else {
                    for (Perk con : talent.connections) {
                        if (data.isAllocated(con)) {
                            set.add(new PerkConnection(CAN_ALLOCATE, talent, con));
                        } else {
                            set.add(new PerkConnection(CANT_ALLOCATE, talent, con));
                        }
                    }
                }

            }

            return set;

        }

        @Override
        public boolean canAllocatePoint(Perk talent, EntityCap.UnitData data) {

            if (getFreePoints(data) > 0 == false) {
                return false;
            }

            if (talent.isStart) {
                if (this.getData()
                        .getAllCurrentTalents()
                        .stream()
                        .anyMatch(x -> x.isStart)) {
                    // if player already picked a starting point, dont allow to pick other start points
                    return false;
                }

                return true;
            }

            boolean can = false;
            for (Perk con : talent.connections) {
                if (this.data.isAllocated(con)) {
                    can = true;
                    break;
                }
            }

            return can;

        }

        @Override
        public boolean hasPerk(Perk perk) {
            return this.getData().isAllocated(perk);
        }

        @Override
        public boolean tryRemovePoint(Perk talent) {
            if (getData().canRemove(talent)) {
                this.getData().remove(talent.GUID());
                return true;
            }
            return false;
        }

        @Override
        public void allocate(Perk talent) {
            this.data.allocate(talent.GUID());
        }

        @Override
        public int getFreePoints(EntityCap.UnitData data) {
            return getAllowedPoints(data) - this.getAllocatedPoints();
        }

        public int getAllowedPoints(EntityCap.UnitData data) {
            return (int) ((float) data.getLevel() * ModConfig.INSTANCE.Server.TALENT_POINTS_PER_LEVEL
                    .get());
        }

        @Override
        public int getAllocatedPoints() {
            return this.data.getAllocatedTalents();
        }

        @Override
        public void reset() {
            this.data.reset();
        }

        @Override
        public void applyStats(EntityCap.UnitData data, PlayerEntity player) {
            this.data.applyStats(data);
        }

        @Override
        public PlayerTalentsData getData() {
            return data;
        }
    }

    public static class Storage extends BaseStorage<IPlayerTalentsData> {

    }

}
