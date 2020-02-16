package com.robertx22.mine_and_slash.database.map_affixes.beneficial;

import com.robertx22.mine_and_slash.database.map_affixes.BeneficialMapAffix;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.LifestealFlat;
import com.robertx22.mine_and_slash.saveclasses.gearitem.StatModData;

import java.util.Arrays;
import java.util.List;

public class BonusLifestealAffix extends BeneficialMapAffix {

    @Override
    public String GUID() {
        return "bonus_lifesteal";
    }

    @Override
    public List<StatModData> Stats(int percent) {
        return Arrays.asList(StatModData.Load(new LifestealFlat().size(StatMod.Size.VERY_HIGH), percent));
    }

}
