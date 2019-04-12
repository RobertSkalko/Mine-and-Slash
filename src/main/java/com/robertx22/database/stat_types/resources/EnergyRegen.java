package com.robertx22.database.stat_types.resources;

import com.robertx22.stats.Stat;
import com.robertx22.uncommon.enumclasses.Elements;

public class EnergyRegen extends Stat {
    public static String GUID = "Energy Regen";

    @Override
    public String unlocString() {
	return "energy_regen";
    }

    public EnergyRegen() {

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
