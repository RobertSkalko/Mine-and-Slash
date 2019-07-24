package com.robertx22.mine_and_slash.database.stats.stat_mods.map_mods.bonus.ele_dmg;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.map_mods.bases.BonusEleDmgBase;
import com.robertx22.mine_and_slash.database.stats.stat_types.generated.ElementalSpellToAttackDMG;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

public class BonusWaterDamageMap extends BonusEleDmgBase {

    public BonusWaterDamageMap() {
    }

    @Override
    public String GUID() {
        return "BonusWaterDamageMap";
    }

    @Override
    public Stat GetBaseStat() {
        return new ElementalSpellToAttackDMG(Elements.Water);
    }

}