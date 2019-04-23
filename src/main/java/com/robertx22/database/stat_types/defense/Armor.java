package com.robertx22.database.stat_types.defense;

import java.util.Arrays;
import java.util.List;

import com.robertx22.stats.IStatEffect;
import com.robertx22.stats.IStatEffects;
import com.robertx22.stats.UsableStat;
import com.robertx22.stats.StatEffects.defense.ArmorEffect;
import com.robertx22.uncommon.enumclasses.Elements;

public class Armor extends UsableStat implements IStatEffects {

    public static String GUID = "Armor";

    @Override
    public String unlocString() {
	return "armor";
    }

    public Armor() {
    }

    @Override
    public String Guid() {
	return GUID;
    }

    @Override
    public boolean ScalesToLevel() {
	return true;
    }

    @Override
    public Elements Element() {
	return Elements.None;
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
	return 10;
    }

    @Override
    public List<IStatEffect> GetEffects() {
	return Arrays.asList(new ArmorEffect());
    }

}
