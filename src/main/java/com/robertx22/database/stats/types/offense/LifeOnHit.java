package com.robertx22.database.stats.types.offense;

import java.util.Arrays;
import java.util.List;

import com.robertx22.stats.IStatEffect;
import com.robertx22.stats.IStatEffects;
import com.robertx22.stats.Stat;
import com.robertx22.stats.StatEffects.LifeOnHitEffect;
import com.robertx22.uncommon.enumclasses.Elements;

public class LifeOnHit extends Stat implements IStatEffects {
	@Override
	public List<IStatEffect> GetEffects() {
		return Arrays.asList(new LifeOnHitEffect());
	}

	public LifeOnHit() {
	}

	@Override
	public String Name() {
		return "Life On Hit";
	}

	@Override
	public boolean ScalesToLevel() {
		return true;
	}

	@Override
	public Elements Element() {
		return null;
	}

	@Override
	public boolean IsPercent() {
		return false;
	}

}
