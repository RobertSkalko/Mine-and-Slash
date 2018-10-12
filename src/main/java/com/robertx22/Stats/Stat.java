package com.robertx22.stats;

import java.util.ArrayList;

import com.robertx22.classes.Unit;
import com.robertx22.enums.Elements;

public abstract class Stat {

	public abstract boolean IsPercent();

	public abstract String Name();

	public abstract boolean ScalesToLevel();

	public abstract Elements Element();

	// public abstract StatRefs StatRef();

	public Double GetValue(Unit Source) {

		return null;

	}

	public ArrayList<IStatEffect> Effects;

	public String ToTooltipString(Unit Source) {

		return Name() + " " + GetValue(Source);
	}

	public boolean IsShownOnTooltip() {
		return true;
	}

}
