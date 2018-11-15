package com.robertx22.customitems.gearitems.bases;

import java.util.HashMap;

import net.minecraft.item.Item;

public abstract class BaseRarityItem extends Item {
	public abstract String Name();

	public BaseRarityItem(int rarity, HashMap<Integer, Item> map) {
		this.setMaxStackSize(1);
		this.setMaxDamage(0);
		// this.setCreativeTab(CreativeTabList.MyModTab);
		this.setUnlocalizedName(Name().toLowerCase() + rarity);
		this.setRegistryName(Name().toLowerCase() + rarity);

		map.put(rarity, this);
	}

}
