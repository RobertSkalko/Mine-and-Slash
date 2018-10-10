package com.robertx22.stats;

import java.util.ArrayList;

import com.robertx22.classes.Unit;
import com.robertx22.enums.*;

public abstract class Stat {

	public abstract String Name();

	public abstract boolean ScalesToLevel();

	public abstract Elements Element();

	public abstract StatRefs StatRef();

	public Double GetValue(Unit Source) {

		return null;
	}

	public ArrayList<IStatEffect> Effects;

}
