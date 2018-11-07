package com.robertx22.database.status.effects.bases;

import java.util.List;

import com.robertx22.saveclasses.gearitem.StatModData;
import com.robertx22.uncommon.utilityclasses.IWeighted;

import net.minecraft.item.Item;

public abstract class BaseStatusEffect implements IWeighted {

	public abstract Item ItemModel();

	public abstract String GUID();

	public abstract List<StatModData> Stats();

	@Override
	public int Weight() {
		return this.UncommonWeight;
	}
}
