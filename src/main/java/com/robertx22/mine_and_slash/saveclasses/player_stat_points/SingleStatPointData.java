package com.robertx22.mine_and_slash.saveclasses.player_stat_points;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.corestats.BaseCoreStatFlat;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.saveclasses.gearitem.StatModData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.IStatsContainer;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;

import java.util.ArrayList;
import java.util.List;

@Storable

public class SingleStatPointData implements IStatsContainer {

    public SingleStatPointData(StatMod mod) {
        this.statmod = mod.GUID();
    }

    public SingleStatPointData() {

    }

    @Store
    public int points = 0;

    @Store
    public String statmod = "";

    public StatMod getMod() {
        return SlashRegistry.StatMods().get(statmod);
    }

    @Override
    public List<LevelAndStats> GetAllStats(int level) {
        List<LevelAndStats> list = new ArrayList<>();
        list.add(new LevelAndStats(StatModData.Load(getMod(), points * 100 / BaseCoreStatFlat.max), level));
        return list;
    }
}
