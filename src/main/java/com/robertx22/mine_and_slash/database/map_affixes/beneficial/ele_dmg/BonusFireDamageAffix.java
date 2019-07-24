package com.robertx22.mine_and_slash.database.map_affixes.beneficial.ele_dmg;

import com.robertx22.mine_and_slash.database.map_affixes.bases.BaseBeneficialEleAffix;
import com.robertx22.mine_and_slash.database.stats.stat_mods.map_mods.bonus.ele_dmg.BonusFireDamageMap;
import com.robertx22.mine_and_slash.saveclasses.gearitem.StatModData;

import java.util.Arrays;
import java.util.List;

public class BonusFireDamageAffix extends BaseBeneficialEleAffix {

    @Override
    public String GUID() {
        return "BonusFireDamageAffix";
    }

    @Override
    public List<StatModData> Stats(int percent) {
        return Arrays.asList(StatModData.Load(new BonusFireDamageMap(), percent));
    }

}
