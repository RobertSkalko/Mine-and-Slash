package com.robertx22.database.stats.types.offense;

import java.util.Arrays;
import java.util.List;

import com.robertx22.stats.IStatEffect;
import com.robertx22.stats.IStatEffects;
import com.robertx22.stats.Stat;
import com.robertx22.stats.StatEffects.LifestealEffect;
import com.robertx22.uncommon.enumclasses.Elements;

public class Lifesteal extends Stat implements IStatEffects {
	@Override
	public List<IStatEffect> GetEffects() {
		return Arrays.asList(new LifestealEffect());
	}

	public Lifesteal() {
	}

	@Override
	public String Name() {
		return "Lifesteal";
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

}
