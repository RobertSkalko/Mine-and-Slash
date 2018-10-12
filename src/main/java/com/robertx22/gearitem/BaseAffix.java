package com.robertx22.gearitem;

import java.util.List;

import com.robertx22.interfaces.IWeighted;
import com.robertx22.stats.StatMod;

public abstract class BaseAffix implements IWeighted {

	public BaseAffix() {
	}

	public abstract String Name();

	public abstract List<StatMod> StatMods();

}
