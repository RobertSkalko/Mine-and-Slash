package com.robertx22.mine_and_slash.database.stats.mods.generated;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.ElementalStatMod;
import com.robertx22.mine_and_slash.database.stats.types.generated.ElementalSpellToAttackDMG;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;
import com.robertx22.mine_and_slash.uncommon.wrappers.MapWrapper;

public class ElementalSpellToAttackDMGFlat extends ElementalStatMod {

    public ElementalSpellToAttackDMGFlat(Elements element) {
        super(element);
    }

    @Override
    public String GUID() {
        return "bonus_" + element.name() + "_damage_flat";
    }

    @Override
    public MapWrapper<Elements, ElementalSpellToAttackDMG> getBaseStatMap() {
        return ElementalSpellToAttackDMG.MAP;
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
    public StatTypes Type() {
        return StatTypes.Flat;
    }

    @Override
    public StatMod newGeneratedInstance(Elements element) {
        return new ElementalSpellToAttackDMGFlat(element);
    }

}
