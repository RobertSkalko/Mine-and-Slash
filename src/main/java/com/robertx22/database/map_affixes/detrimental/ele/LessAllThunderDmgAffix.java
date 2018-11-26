package com.robertx22.database.map_affixes.detrimental.ele;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.map_affixes.DetrimentalMapAffix;
import com.robertx22.database.map_mods.minus.all_ele_dmg.LessAllThunderDamageMap;
import com.robertx22.saveclasses.gearitem.StatModData;

public class LessAllThunderDmgAffix extends DetrimentalMapAffix {

	@Override
	public String Name() {
		return "LessAllThunderDmgAffix";
	}

	@Override
	public List<StatModData> Stats(int percent) {
		return Arrays.asList(StatModData.NewStatusEffect(percent, new LessAllThunderDamageMap()));
	}

}
