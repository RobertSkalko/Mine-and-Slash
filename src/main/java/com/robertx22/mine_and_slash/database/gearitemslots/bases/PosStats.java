package com.robertx22.mine_and_slash.database.gearitemslots.bases;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.uncommon.interfaces.IWeighted;

import java.util.ArrayList;
import java.util.List;

public class PosStats implements IWeighted {

    public PosStats(List<StatMod> mods) {
        this.mods = mods;
    }

    public PosStats(StatMod mod) {
        this.mods = new ArrayList<StatMod>();
        this.mods.add(mod);
    }

    public PosStats(StatMod mod, StatMod mod2) {
        this.mods = new ArrayList<StatMod>();
        this.mods.add(mod);
        this.mods.add(mod2);
    }

    public List<StatMod> mods;
    public int weight = 1000;

    public PosStats weight(int w) {
        this.weight = w;
        return this;
    }

    @Override
    public int Weight() {
        return weight;
    }
}
