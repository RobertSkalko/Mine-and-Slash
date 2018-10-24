package com.robertx22.database.stats.types.elementals.pene;

import java.util.Arrays;
import java.util.List;

import com.robertx22.stats.IStatEffect;
import com.robertx22.stats.IStatEffects;
import com.robertx22.stats.Stat;
import com.robertx22.stats.StatEffects.ElementalPeneEffect;
import com.robertx22.uncommon.enumclasses.Elements;

public class NaturePene extends Stat implements IStatEffects {

	@Override
	public List<IStatEffect> GetEffects() {
		return Arrays.asList(new ElementalPeneEffect());
	}

	public NaturePene() {
	}

	@Override
	public String Name() {
		return "Nature Penetration";
	}

	@Override
	public boolean ScalesToLevel() {
		return true;
	}

	@Override
	public Elements Element() {
		return Elements.Nature;
	}

	@Override
	public boolean IsPercent() {
		return false;
	}

}
