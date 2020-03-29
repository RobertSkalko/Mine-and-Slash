package com.robertx22.mine_and_slash.database.stats.mods.generated;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.ElementalStatMod;
import com.robertx22.mine_and_slash.database.stats.types.generated.ElementalInfusion;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;
import com.robertx22.mine_and_slash.uncommon.wrappers.MapWrapper;

public class ElementalSpellToAttackDMGFlat extends ElementalStatMod {

    public ElementalSpellToAttackDMGFlat(Elements element) {
        super(element);
    }

    @Override
    public MapWrapper<Elements, ElementalInfusion> getBaseStatMap() {
        return ElementalInfusion.MAP;
    }

    @Override
    public float Min() {
        return 3;
    }

    @Override
    public float Max() {
        return 15;
    }

    @Override
    public StatModTypes getModType() {
        return StatModTypes.Flat;
    }

    @Override
    public StatMod newGeneratedInstance(Elements element) {
        return new ElementalSpellToAttackDMGFlat(element);
    }

}
