package com.robertx22.mine_and_slash.uncommon.capability;

import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.PlayerStatPointsData;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.SingleStatPointData;
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

import java.util.Optional;

@Mod.EventBusSubscriber
public class PlayerStatsPointsCap {

    public static final ResourceLocation RESOURCE = new ResourceLocation(Ref.MODID, "player_stat_points");
    private static final String LOC = "PLAYER_STAT_POINTS_DATA";

    @CapabilityInject(IPlayerStatPointsData.class)
    public static final Capability<IPlayerStatPointsData> Data = null;

    public interface IPlayerStatPointsData extends ICommonCapability {

        public SingleStatPointData getStatData(String statmodguid);

        int getAvailablePoints(EntityCap.UnitData data);

        boolean hasAvailablePoints(EntityCap.UnitData data);

        PlayerStatPointsData getData();

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

    public static class Provider extends BaseProvider<IPlayerStatPointsData> {

        @Override
        public IPlayerStatPointsData defaultImpl() {
            return new DefaultImpl();
        }

        @Override
        public Capability<IPlayerStatPointsData> dataInstance() {
            return Data;
        }
    }

    public static class DefaultImpl implements IPlayerStatPointsData {

        PlayerStatPointsData data = new PlayerStatPointsData();

        @Override
        public CompoundNBT getNBT() {
            CompoundNBT nbt = new CompoundNBT();
            LoadSave.Save(data, nbt, LOC);
            return nbt;
        }

        @Override
        public void setNBT(CompoundNBT nbt) {
            this.data = LoadSave.Load(PlayerStatPointsData.class, new PlayerStatPointsData(), nbt, LOC);
        }

        @Override
        public int getAvailablePoints(EntityCap.UnitData data) {
            return getPointsForLevel(data.getLevel()) - this.data.getCurrentlyAllocatedPointAmount();
        }

        public int getPointsForLevel(int lvl) {
            return 1 * lvl;
        }

        @Override
        public boolean hasAvailablePoints(EntityCap.UnitData data) {
            return getAvailablePoints(data) > 0;
        }

        @Override
        public PlayerStatPointsData getData() {
            return data;
        }

        @Override
        public SingleStatPointData getStatData(String statmodguid) {

            Optional<SingleStatPointData> opt = data.getAllStatDatas()
                    .stream()
                    .filter(x -> x.statmod.equals(statmodguid))
                    .findFirst();

            if (opt.isPresent()) {
                return opt.get();
            }

            return null;
        }

    }

    public static class Storage extends BaseStorage<IPlayerStatPointsData> {

    }

}
