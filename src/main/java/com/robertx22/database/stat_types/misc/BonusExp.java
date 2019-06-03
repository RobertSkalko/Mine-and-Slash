package com.robertx22.database.stat_types.misc;

import com.robertx22.database.stats.Stat;
import com.robertx22.uncommon.enumclasses.Elements;

public class BonusExp extends Stat {
    public static String GUID = "bonusexpflat";
    
    @Override
    public String unlocString() {
	return "bonusexpflat";
    }

    public BonusExp() {
    }

    @Override
    public String Guid() {
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
	return true;
    }

}