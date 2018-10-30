package com.robertx22.customitems.bases;

import java.util.HashMap;

import com.robertx22.customitems.oldreplacesoon.NewBlocks;

import net.minecraft.item.Item;

public abstract class BaseBow extends net.minecraft.item.ItemBow {
	// static net.minecraft.item.ItemBow.ToolMaterial Mat =
	// EnumHelper.addToolMaterial("swordmat", 0, 900, 1F, 1F, 1);

	public abstract String Name();

	public BaseBow(int rarity, HashMap<Integer, Item> map) {
		super();
		this.setMaxStackSize(1);
		this.setMaxDamage(1000);
		this.setCreativeTab(NewBlocks.MyModTab);
		this.setUnlocalizedName(Name().toLowerCase() + rarity);
		this.setRegistryName("bow/" + Name().toLowerCase() + rarity);

		map.put(rarity, this);
	}
}