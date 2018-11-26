package com.robertx22.database.map_affixes.detrimental.ele;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.map_affixes.DetrimentalMapAffix;
import com.robertx22.database.map_mods.minus.all_ele_dmg.LessAllNatureDamageMap;
import com.robertx22.saveclasses.gearitem.StatModData;

public class LessAllNatureDmgAffix extends DetrimentalMapAffix {

	@Override
	public String Name() {
		return "LessAllNatureDmgAffix";
	}

	@Override
	public List<StatModData> Stats(int percent) {
		return Arrays.asList(StatModData.NewStatusEffect(percent, new LessAllNatureDamageMap()));
	}

}
