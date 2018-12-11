package com.robertx22.database.stat_types.offense;

import java.util.Arrays;
import java.util.List;

import com.robertx22.stats.IStatEffect;
import com.robertx22.stats.IStatEffects;
import com.robertx22.stats.Stat;
import com.robertx22.stats.StatEffects.offense.CriticalDamageEffect;
import com.robertx22.uncommon.enumclasses.Elements;

public class CriticalDamage extends Stat implements IStatEffects {
    public static String GUID = "Critical Damage";

    @Override
    public String unlocString() {
	return "critical_damage";
    }

    @Override
    public List<IStatEffect> GetEffects() {
	return Arrays.asList(new CriticalDamageEffect());
    }

    public CriticalDamage() {
	this.BaseFlat = 20;
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
