package com.robertx22.database.stats.types.traits;

import java.util.Arrays;
import java.util.List;

import com.robertx22.stats.IStatEffect;
import com.robertx22.stats.Trait;
import com.robertx22.stats.StatEffects.traits.GolemEffect;
import com.robertx22.uncommon.enumclasses.Elements;

public class Golem extends Trait {

	@Override
	public List<IStatEffect> GetEffects() {
		return Arrays.asList(new GolemEffect());
	}

	@Override
	public boolean IsPercent() {
		return false;
	}

	@Override
	public String Name() {
		return "Golem";
	}

	@Override
	public boolean ScalesToLevel() {
		return false;
	}

	@Override
	public Elements Element() {
		return null;
	}

}
