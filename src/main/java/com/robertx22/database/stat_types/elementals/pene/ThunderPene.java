package com.robertx22.database.stat_types.elementals.pene;

import java.util.Arrays;
import java.util.List;

import com.robertx22.stats.IStatEffect;
import com.robertx22.stats.IStatEffects;
import com.robertx22.stats.Stat;
import com.robertx22.stats.StatEffects.ElementalPeneEffect;
import com.robertx22.uncommon.enumclasses.Elements;

public class ThunderPene extends Stat implements IStatEffects {

	public static String GUID = "Thunder Penetration";

	@Override
	public List<IStatEffect> GetEffects() {
		return Arrays.asList(new ElementalPeneEffect());
	}

	public ThunderPene() {
	}

	@Override
	public String Name() {
		return GUID;
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
