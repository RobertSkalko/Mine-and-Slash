package com.robertx22.database.stat_types.elementals.pene;

import com.robertx22.stats.IStatEffects;
import com.robertx22.stats.Stat;

public abstract class BasePene extends Stat implements IStatEffects {

    @Override
    public boolean ScalesToLevel() {
	return true;
    }

    @Override
    public boolean IsPercent() {
	return false;
    }

}
