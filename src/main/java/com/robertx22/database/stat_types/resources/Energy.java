package com.robertx22.database.stat_types.resources;

import com.robertx22.stats.FillableStat;
import com.robertx22.uncommon.enumclasses.Elements;

public class Energy extends FillableStat {
    public static String GUID = "Energy";

    public Energy() {

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
	return false;
    }

    @Override
    public String unlocString() {
	return "energy";
    }
}
