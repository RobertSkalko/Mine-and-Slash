package com.robertx22.database.unique_items;

import java.util.List;

import com.robertx22.stats.StatMod;

import net.minecraft.item.Item;

public abstract class BaseUniqueItem {

	public abstract String GUID();

	public abstract String Name();

	public abstract String Desc();

	public abstract List<StatMod> UniqueStats();

	public abstract Item ItemStack();

	public abstract int Tier();

}
