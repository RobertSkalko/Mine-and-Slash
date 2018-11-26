package com.robertx22.database.map_affixes.detrimental;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.map_affixes.DetrimentalMapAffix;
import com.robertx22.database.map_mods.minus.LessHealthRegenMap;
import com.robertx22.saveclasses.gearitem.StatModData;

public class LessHealthRegenAffix extends DetrimentalMapAffix {

	@Override
	public String Name() {
		return "LessHealthRegenAffix";
	}

	@Override
	public List<StatModData> Stats(int percent) {
		return Arrays.asList(StatModData.NewStatusEffect(percent, new LessHealthRegenMap()));
	}

}
