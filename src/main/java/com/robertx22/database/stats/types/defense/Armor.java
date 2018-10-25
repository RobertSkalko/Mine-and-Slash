package com.robertx22.database.stats.types.defense;

import java.util.Arrays;
import java.util.List;

import com.robertx22.stats.IStatEffect;
import com.robertx22.stats.IStatEffects;
import com.robertx22.stats.UsableStat;
import com.robertx22.stats.StatEffects.defense.ArmorEffect;
import com.robertx22.uncommon.enumclasses.Elements;

public class Armor extends UsableStat implements IStatEffects {

	public static String GUID = "Armor";

	public Armor() {
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
		return null;
	}

	@Override
	public boolean IsPercent() {
		return false;
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
