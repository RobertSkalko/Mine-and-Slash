package com.robertx22.database.stat_types.resources;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stats.IStatEffect;
import com.robertx22.database.stats.IStatEffects;
import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_effects.LifeOnHitEffect;
import com.robertx22.uncommon.enumclasses.Elements;

public class LifeOnHit extends Stat implements IStatEffects {
    public static String GUID = "Life On Hit";

    @Override
    public List<IStatEffect> GetEffects() {
	return Arrays.asList(new LifeOnHitEffect());
    }

    @Override
    public String unlocString() {
	return "life_on_hit";
    }

    public LifeOnHit() {
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

}
