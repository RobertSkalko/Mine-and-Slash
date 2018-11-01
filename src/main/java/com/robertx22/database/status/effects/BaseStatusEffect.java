package com.robertx22.database.status.effects;

import java.util.List;

import com.robertx22.saveclasses.gearitem.StatModData;

import net.minecraft.item.Item;

public abstract class BaseStatusEffect {

	public abstract Item ItemModel();

	public abstract String GUID();

	public abstract List<StatModData> Stats();
}
