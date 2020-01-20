package com.robertx22.mine_and_slash.uncommon.capability;

import com.robertx22.mine_and_slash.config.ModConfig;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.network.sync_cap.CapTypes;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
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

        public SingleStatPointData getStatData(LvlPointStat stat);

        public void addPoint(PlayerEntity player, LvlPointStat stat,
                             EntityCap.UnitData data);

        int getAvailablePoints(EntityCap.UnitData data);

        boolean hasAvailablePoints(EntityCap.UnitData data);

        void resetStats();

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
        public CapTypes getCapType() {
            return CapTypes.STAT_POINTS;
        }

        @Override
        public void setNBT(CompoundNBT nbt) {
            this.data = LoadSave.Load(PlayerStatPointsData.class, new PlayerStatPointsData(), nbt, LOC);
        }

        @Override
        public int getAvailablePoints(EntityCap.UnitData data) {

            int avail = getPointsForLevel(data.getLevel());

            return avail - this.data.getCurrentlyAllocatedPointAmount();
        }

        public int getPointsForLevel(int lvl) {
            return (int) (lvl * ModConfig.INSTANCE.Server.STAT_POINTS_PER_LEVEL.get());
        }

        @Override
        public boolean hasAvailablePoints(EntityCap.UnitData data) {
            return getAvailablePoints(data) > 0;
        }

        @Override
        public void resetStats() {
            data.getAllStatDatas().forEach(x -> x.points = 0);
        }

        @Override
        public PlayerStatPointsData getData() {
            return data;
        }

        @Override
        public SingleStatPointData getStatData(LvlPointStat stat) {

            Optional<SingleStatPointData> opt = data.getAllStatDatas()
                    .stream()
                    .filter(x -> x.stat.equals(stat))
                    .findFirst();

            if (opt.isPresent()) {
                return opt.get();
            }

            return new SingleStatPointData();
        }

        @Override
        public void addPoint(PlayerEntity player, LvlPointStat stat,
                             EntityCap.UnitData data) {
            if (this.hasAvailablePoints(data)) {
                getStatData(stat).points++;
                data.setEquipsChanged(true);
                data.recalculateStats(player);
                data.syncToClient(player);
            }
        }

    }

    public static class Storage extends BaseStorage<IPlayerStatPointsData> {

    }

}
