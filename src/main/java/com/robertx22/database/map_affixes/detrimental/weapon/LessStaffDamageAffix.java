package com.robertx22.database.map_affixes.detrimental.weapon;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.map_affixes.DetrimentalMapAffix;
import com.robertx22.database.map_mods.minus.weapon.LessStaffDamageMap;
import com.robertx22.saveclasses.gearitem.StatModData;

public class LessStaffDamageAffix extends DetrimentalMapAffix {

    @Override
    public String Name() {
	return "LessStaffDamageAffix";
    }

    @Override
    public List<StatModData> Stats(int percent) {
	return Arrays.asList(StatModData.NewStatusEffect(percent, new LessStaffDamageMap()));

    }

}
