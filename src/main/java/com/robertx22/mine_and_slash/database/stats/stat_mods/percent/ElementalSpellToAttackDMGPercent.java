package com.robertx22.mine_and_slash.database.stats.stat_mods.percent;

import com.robertx22.mine_and_slash.database.ElementalStatMod;
import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_types.generated.ElementalSpellToAttackDMG;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;

public class ElementalSpellToAttackDMGPercent extends ElementalStatMod {

    public ElementalSpellToAttackDMGPercent(Elements element) {
        super(element);
    }

    @Override
    public Stat GetBaseStat() {
        return new ElementalSpellToAttackDMG(this.element);
    }

    @Override
    public float Min() {
        return 5;
    }

    @Override
    public float Max() {
        return 20;
    }

    @Override
    public StatTypes Type() {
        return StatTypes.Percent;
    }

    @Override
    public StatMod newGeneratedInstance(Elements element) {
        return new ElementalSpellToAttackDMGPercent(element);
    }

}
