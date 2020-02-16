package com.robertx22.mine_and_slash.database.stats.mods.generated;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

public class HighElementalSpellToAttackDMGFlat extends ElementalSpellToAttackDMGFlat {
    public HighElementalSpellToAttackDMGFlat(Elements element) {
        super(element);
    }

    @Override
    public float Min() {
        return super.Min() * 2F;
    }

    @Override
    public float Max() {
        return super.Max() * 2;
    }

    @Override
    public String GUID() {
        return "high_" + super.GUID();
    }

    @Override
    public StatMod newGeneratedInstance(Elements element) {
        return new HighElementalSpellToAttackDMGFlat(element);
    }
}
