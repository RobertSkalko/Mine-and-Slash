package com.robertx22.database.map_affixes.detrimental;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.map_affixes.DetrimentalMapAffix;
import com.robertx22.database.map_mods.minus.LessEnergyRegenMap;
import com.robertx22.saveclasses.gearitem.StatModData;

public class LessEnergyRegenAffix extends DetrimentalMapAffix {

	@Override
	public String Name() {
		return "LessEnergyRegenAffix";
	}

	@Override
	public List<StatModData> Stats(int percent) {
		return Arrays.asList(StatModData.NewStatusEffect(percent, new LessEnergyRegenMap()));
	}

}
