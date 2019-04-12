package com.robertx22.database.stat_types.resources;

import com.robertx22.stats.FillableStat;
import com.robertx22.uncommon.enumclasses.Elements;

public class Mana extends FillableStat {
    public static String GUID = "Mana";

    public Mana() {

    }

    @Override
    public String unlocString() {
	return "mana";
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

}
