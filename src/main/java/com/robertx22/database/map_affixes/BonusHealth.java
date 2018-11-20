package com.robertx22.database.map_affixes;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.map_mods.HealthMap;
import com.robertx22.saveclasses.gearitem.StatModData;

public class BonusHealth extends BaseMapAffix {

	@Override
	public String Name() {
		return "Bonus Health";
	}

	@Override
	public List<StatModData> Stats(int percent) {
		return Arrays.asList(StatModData.NewStatusEffect(percent, new HealthMap()));
	}

}
