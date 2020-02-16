package com.robertx22.mine_and_slash.database.stats.mods.percent;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

public class HighElementalSpellToAttackDMGPercent extends ElementalSpellToAttackDMGPercent {
    public HighElementalSpellToAttackDMGPercent(Elements element) {
        super(element);
    }

    @Override
    public String GUID() {
        return "high_" + super.GUID();
    }

    @Override
    public float Min() {
        return super.Min() * 2;
    }

    @Override
    public float Max() {
        return super.Max() * 2;
    }

    @Override
    public StatMod newGeneratedInstance(Elements element) {
        return new HighElementalSpellToAttackDMGPercent(element);
    }
}
