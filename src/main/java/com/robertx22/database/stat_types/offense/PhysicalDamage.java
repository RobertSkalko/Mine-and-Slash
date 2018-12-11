package com.robertx22.database.stat_types.offense;

import com.robertx22.stats.Stat;
import com.robertx22.uncommon.enumclasses.Elements;

public class PhysicalDamage extends Stat {
    public static String GUID = "Physical Damage";

    public PhysicalDamage() {
	this.StatMinimum = 1;
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
	return null;
    }

    @Override
    public boolean IsPercent() {
	return false;
    }

    @Override
    public String unlocString() {
	return "physical_damage";
    }
}
