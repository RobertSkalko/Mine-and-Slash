package com.robertx22.gearitem;

import java.util.List;

import com.robertx22.stats.StatMod;
import com.robertx22.uncommon.utilityclasses.IWeighted;

public abstract class BaseAffix implements IWeighted {

	public BaseAffix() {
	}

	public abstract String Name();

	@Override
	public int Weight() {
		return IWeighted.NormalWeight;
	}

	public abstract List<StatMod> StatMods();

}
