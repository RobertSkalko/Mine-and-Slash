package com.robertx22.database.stats.types;

import java.util.Arrays;
import java.util.List;

import com.robertx22.enumclasses.Elements;
import com.robertx22.stats.IStatEffect;
import com.robertx22.stats.IStatEffects;
import com.robertx22.stats.UsableStat;
import com.robertx22.stats.StatEffects.ArmorEffect;

public class Armor extends UsableStat implements IStatEffects {

	public Armor() {
	}

	@Override
	public String Name() {
		return "Armor";
	}

	@Override
	public boolean ScalesToLevel() {
		return false;
	}

	@Override
	public Elements Element() {
		return null;
	}

	@Override
	public boolean IsPercent() {
		return true;
	}

	@Override
	public float MaximumPercent() {
		return 0.75F;
	}

	@Override
	public int AverageStat() {
		return 50;
	}

	@Override
	public List<IStatEffect> GetEffects() {
		return Arrays.asList(new ArmorEffect());
	}

}
