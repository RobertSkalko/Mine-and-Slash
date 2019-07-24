package com.robertx22.mine_and_slash.database.map_affixes.beneficial.ele_res;

import com.robertx22.mine_and_slash.database.map_affixes.bases.BaseBeneficialEleAffix;
import com.robertx22.mine_and_slash.database.stats.stat_mods.map_mods.bonus.ele_res.BonusNatureResistMap;
import com.robertx22.mine_and_slash.saveclasses.gearitem.StatModData;

import java.util.Arrays;
import java.util.List;

public class BonusNatureResistAffix extends BaseBeneficialEleAffix {

    @Override
    public String GUID() {
        return "BonusNatureResistAffix";
    }

    @Override
    public List<StatModData> Stats(int percent) {
        return Arrays.asList(StatModData.Load(new BonusNatureResistMap(), percent));

    }

}
