package com.robertx22.database.map_affixes.detrimental.ele;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.map_affixes.DetrimentalMapAffix;
import com.robertx22.database.map_mods.minus.all_ele_dmg.LessAllFireDamageMap;
import com.robertx22.saveclasses.gearitem.StatModData;

public class LessAllFireDmgAffix extends DetrimentalMapAffix {

	@Override
	public String Name() {
		return "LessAllFireDmgAffix";
	}

	@Override
	public List<StatModData> Stats(int percent) {
		return Arrays.asList(StatModData.NewStatusEffect(percent, new LessAllFireDamageMap()));
	}

}