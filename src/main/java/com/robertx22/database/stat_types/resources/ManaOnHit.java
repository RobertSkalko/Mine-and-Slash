package com.robertx22.database.stat_types.resources;

import java.util.Arrays;
import java.util.List;

import com.robertx22.stats.IStatEffect;
import com.robertx22.stats.IStatEffects;
import com.robertx22.stats.Stat;
import com.robertx22.stats.StatEffects.ManaOnHitEffect;
import com.robertx22.uncommon.enumclasses.Elements;

public class ManaOnHit extends Stat implements IStatEffects {
	public static String GUID = "Mana On Hit";

	@Override
	public List<IStatEffect> GetEffects() {
		return Arrays.asList(new ManaOnHitEffect());
	}

	public ManaOnHit() {
	}

	@Override
	public String Name() {
		return GUID;
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
		return false;
	}

}