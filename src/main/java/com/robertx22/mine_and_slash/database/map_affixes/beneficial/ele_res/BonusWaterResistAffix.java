package com.robertx22.mine_and_slash.database.map_affixes.beneficial.ele_res;

import com.robertx22.mine_and_slash.database.map_affixes.bases.BaseBeneficialEleAffix;
import com.robertx22.mine_and_slash.database.stats.stat_mods.map_mods.bonus.ele_res.BonusWaterResistMap;
import com.robertx22.mine_and_slash.saveclasses.gearitem.StatModData;

import java.util.Arrays;
import java.util.List;

public class BonusWaterResistAffix extends BaseBeneficialEleAffix {

    @Override
    public String GUID() {
        return "BonusWaterResistAffix";
    }

    @Override
    public List<StatModData> Stats(int percent) {
        return Arrays.asList(StatModData.Load(new BonusWaterResistMap(), percent));

    }

}
