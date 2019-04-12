package com.robertx22.database.stat_types.resources;

import com.robertx22.stats.Stat;
import com.robertx22.uncommon.enumclasses.Elements;

public class ManaRegen extends Stat {
    public static String GUID = "Mana Regen";

    public ManaRegen() {

    }

    @Override
    public String unlocString() {
	return "mana_regen";
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
