package com.robertx22.database.stats.types.elementals.resist;

import java.util.Arrays;
import java.util.List;

import com.robertx22.stats.IStatEffect;
import com.robertx22.stats.IStatEffects;
import com.robertx22.stats.UsableStat;
import com.robertx22.stats.StatEffects.ElementalResistEffect;
import com.robertx22.uncommon.enumclasses.Elements;

public class ThunderResist extends UsableStat implements IStatEffects {

	@Override
	public List<IStatEffect> GetEffects() {
		return Arrays.asList(new ElementalResistEffect());
	}

	@Override
	public float MaximumPercent() {
		return 0.75F;
	}

	@Override
	public int AverageStat() {
		return 35;
	}

	public ThunderResist() {
	}

	@Override
	public String Name() {
		return "Thunder Resist";
	}

	@Override
	public boolean ScalesToLevel() {
		return true;
	}

	@Override
	public Elements Element() {
		return Elements.Thunder;
	}

	@Override
	public boolean IsPercent() {
		return false;
	}

}
