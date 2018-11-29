package com.robertx22.database.map_affixes.detrimental.weapon;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.map_affixes.DetrimentalMapAffix;
import com.robertx22.database.map_mods.minus.weapon.LessHammerDamageMap;
import com.robertx22.saveclasses.gearitem.StatModData;

public class LessHammerDamageAffix extends DetrimentalMapAffix {

    @Override
    public String Name() {
	return "LessHammerDamageAffix";
    }

    @Override
    public List<StatModData> Stats(int percent) {
	return Arrays.asList(StatModData.NewStatusEffect(percent, new LessHammerDamageMap()));

    }

}
