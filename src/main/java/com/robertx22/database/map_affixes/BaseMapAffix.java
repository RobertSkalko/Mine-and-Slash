package com.robertx22.database.map_affixes;

import java.util.List;

import com.robertx22.saveclasses.gearitem.StatModData;
import com.robertx22.uncommon.utilityclasses.IWeighted;

public abstract class BaseMapAffix implements IWeighted {

	public abstract String Name();

	public String GUID() {
		return Name();
	}

	public abstract List<StatModData> Stats(int percent);

	@Override
	public int Weight() {
		return this.UncommonWeight;
	}

	public abstract boolean isBeneficial();
}
