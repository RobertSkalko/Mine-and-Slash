package com.robertx22.mine_and_slash.database.map_affixes.beneficial;

import com.robertx22.mine_and_slash.database.map_affixes.BeneficialMapAffix;
import com.robertx22.mine_and_slash.database.stats.stat_mods.map_mods.bonus.BonusLifestealMap;
import com.robertx22.mine_and_slash.saveclasses.gearitem.StatModData;

import java.util.Arrays;
import java.util.List;

public class BonusLifestealAffix extends BeneficialMapAffix {

    @Override
    public String GUID() {
        return "Bonus Lifesteal";
    }

    @Override
    public List<StatModData> Stats(int percent) {
        return Arrays.asList(StatModData.Load(new BonusLifestealMap(), percent));
    }

}
