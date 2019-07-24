package com.robertx22.mine_and_slash.spells.bases;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.saveclasses.Unit;

public class EffectCalculation {

    public Stat stat;
    public float Multi;

    public EffectCalculation(Stat stat, float multi) {
        super();
        this.stat = stat;
        Multi = multi;
    }

    public Stat GetStat() {
        return stat;
    }

    public int GetValue(Unit unit) {
        return (int) (unit.getStat(stat).Value * Multi);
    }

}
