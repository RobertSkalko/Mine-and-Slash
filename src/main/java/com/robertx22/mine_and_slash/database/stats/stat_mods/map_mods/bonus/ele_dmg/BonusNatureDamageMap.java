package com.robertx22.mine_and_slash.database.stats.stat_mods.map_mods.bonus.ele_dmg;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.map_mods.bases.BonusEleDmgBase;
import com.robertx22.mine_and_slash.database.stats.stat_types.generated.ElementalSpellToAttackDMG;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

public class BonusNatureDamageMap extends BonusEleDmgBase {

    public BonusNatureDamageMap() {
    }

    @Override
    public String GUID() {
        return "BonusNatureDamageMap";
    }

    @Override
    public Stat GetBaseStat() {
        return new ElementalSpellToAttackDMG(Elements.Nature);
    }

}