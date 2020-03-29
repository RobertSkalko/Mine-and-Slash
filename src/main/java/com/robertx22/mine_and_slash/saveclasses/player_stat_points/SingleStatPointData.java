package com.robertx22.mine_and_slash.saveclasses.player_stat_points;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.IStatsContainer;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;

import java.util.Arrays;
import java.util.List;

@Storable

public class SingleStatPointData implements IStatsContainer {

    public SingleStatPointData(LvlPointStat stat) {
        this.stat = stat;
    }

    public SingleStatPointData() {

    }

    @Store
    public int points = 0;

    @Store
    public LvlPointStat stat = LvlPointStat.VITALITY;

    public Stat getStat() {
        return SlashRegistry.Stats()
            .get(stat.statguid);
    }

    @Override
    public List<StatData> GetAllStats(int level) {
        StatData data = new StatData(getStat());
        data.addFlat(points);
        return Arrays.asList(data);
    }

}
