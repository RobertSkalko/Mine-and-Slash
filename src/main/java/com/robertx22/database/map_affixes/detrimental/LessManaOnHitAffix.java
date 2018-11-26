package com.robertx22.database.map_affixes.detrimental;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.map_affixes.DetrimentalMapAffix;
import com.robertx22.database.map_mods.minus.LessManaOnHitMap;
import com.robertx22.saveclasses.gearitem.StatModData;

public class LessManaOnHitAffix extends DetrimentalMapAffix {

	@Override
	public String Name() {
		return "LessManaOnHitAffix";
	}

	@Override
	public List<StatModData> Stats(int percent) {
		return Arrays.asList(StatModData.NewStatusEffect(percent, new LessManaOnHitMap()));

	}

}
