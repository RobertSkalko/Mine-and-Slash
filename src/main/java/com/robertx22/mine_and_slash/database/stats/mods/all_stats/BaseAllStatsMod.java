package com.robertx22.mine_and_slash.database.stats.mods.all_stats;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.types.BaseTrait;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.uncommon.interfaces.IGenerated;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseAllStatsMod extends StatMod implements IGenerated<StatMod> {

    public Stat stat;

    public BaseAllStatsMod(Stat stat) {
        this.stat = stat;
    }

    @Override
    public Stat GetBaseStat() {
        return stat;
    }

    public abstract BaseAllStatsMod newGeneratedInstance(Stat stat);

    @Override
    public List<StatMod> generateAllPossibleStatVariations() {
        List<StatMod> list = new ArrayList<>();
        SlashRegistry.Stats()
                .getFiltered(x -> x instanceof BaseTrait == false)
                .forEach(x -> list.add(newGeneratedInstance(x)));
        return list;
    }
}

