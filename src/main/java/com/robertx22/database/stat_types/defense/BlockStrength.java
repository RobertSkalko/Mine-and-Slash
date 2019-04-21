package com.robertx22.database.stat_types.defense;

import com.robertx22.stats.Stat;
import com.robertx22.uncommon.enumclasses.Elements;

public class BlockStrength extends Stat {
    public static String GUID = "BlockStrength";

    public BlockStrength() {
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
	return "block_strength";
    }
}
