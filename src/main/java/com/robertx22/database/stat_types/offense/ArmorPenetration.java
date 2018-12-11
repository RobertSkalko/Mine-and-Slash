package com.robertx22.database.stat_types.offense;

import java.util.Arrays;
import java.util.List;

import com.robertx22.stats.IStatEffect;
import com.robertx22.stats.IStatEffects;
import com.robertx22.stats.Stat;
import com.robertx22.stats.StatEffects.offense.PenetrationEffect;
import com.robertx22.uncommon.enumclasses.Elements;

public class ArmorPenetration extends Stat implements IStatEffects {
    public static String GUID = "Armor Penetration";

    @Override
    public String unlocString() {
	return "armor_penetration";
    }

    public ArmorPenetration() {
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
    public List<IStatEffect> GetEffects() {
	return Arrays.asList(new PenetrationEffect());
    }

}
