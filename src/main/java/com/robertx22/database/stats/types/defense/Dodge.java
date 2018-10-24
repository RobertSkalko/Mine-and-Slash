package com.robertx22.database.stats.types.defense;

import java.util.Arrays;
import java.util.List;

import com.robertx22.stats.IStatEffect;
import com.robertx22.stats.IStatEffects;
import com.robertx22.stats.Stat;
import com.robertx22.stats.StatEffects.defense.DodgeEffect;
import com.robertx22.uncommon.enumclasses.Elements;

public class Dodge extends Stat implements IStatEffects {

	@Override
	public List<IStatEffect> GetEffects() {
		return Arrays.asList(new DodgeEffect());
	}

	public Dodge() {
	}

	@Override
	public String Name() {
		return "Dodge";
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
