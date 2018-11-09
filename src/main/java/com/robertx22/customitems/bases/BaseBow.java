package com.robertx22.customitems.bases;

import java.util.HashMap;

import com.robertx22.customitems.oldreplacesoon.NewBlocks;

import net.minecraft.item.Item;

public abstract class BaseBow extends net.minecraft.item.ItemBow {

	public abstract String Name();

	public BaseBow(int rarity, HashMap<Integer, Item> map) {
		super();
		this.setMaxStackSize(1);
		this.setMaxDamage(BaseArmorItem.MAX_GEAR_DURABILITY);
		this.setCreativeTab(NewBlocks.MyModTab);
		this.setUnlocalizedName(Name().toLowerCase() + rarity);
		this.setRegistryName("bow/" + Name().toLowerCase() + rarity);

		map.put(rarity, this);
	}
}