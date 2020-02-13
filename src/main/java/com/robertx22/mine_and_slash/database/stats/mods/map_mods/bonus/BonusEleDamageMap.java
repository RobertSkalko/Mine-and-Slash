package com.robertx22.mine_and_slash.database.stats.mods.map_mods.bonus;

import com.robertx22.mine_and_slash.database.IGUID;
import com.robertx22.mine_and_slash.database.stats.mods.ElementalStatMod;
import com.robertx22.mine_and_slash.database.stats.types.generated.ElementalSpellToAttackDMG;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;
import com.robertx22.mine_and_slash.uncommon.wrappers.MapWrapper;

public class BonusEleDamageMap extends ElementalStatMod {

    public BonusEleDamageMap(Elements element) {
        super(element);

    }

    @Override
    public MapWrapper getBaseStatMap() {
        return ElementalSpellToAttackDMG.MAP;
    }

    @Override
    public float Min() {
        return 0;
    }

    @Override
    public float Max() {
        return 75;
    }

    @Override
    public StatTypes Type() {
        return StatTypes.Flat;
    }

    @Override
    public String GUID() {
        return "bonus_" + element.guidName + "_damage_map";
    }

    @Override
    public IGUID newGeneratedInstance(Elements element) {
        return new BonusEleDamageMap(element);
    }
}