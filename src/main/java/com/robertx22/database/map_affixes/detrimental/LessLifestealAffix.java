package com.robertx22.database.map_affixes.detrimental;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.map_affixes.DetrimentalMapAffix;
import com.robertx22.database.map_mods.minus.LessLifestealMap;
import com.robertx22.saveclasses.gearitem.StatModData;

public class LessLifestealAffix extends DetrimentalMapAffix {

	@Override
	public String Name() {
		return "LessLifestealAffix";
	}

	@Override
	public List<StatModData> Stats(int percent) {
		return Arrays.asList(StatModData.NewStatusEffect(percent, new LessLifestealMap()));
	}

}
