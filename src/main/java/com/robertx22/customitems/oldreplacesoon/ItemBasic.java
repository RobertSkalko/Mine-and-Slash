package com.robertx22.customitems.oldreplacesoon;

import com.robertx22.uncommon.utilityclasses.RegisterUtils;

import net.minecraft.item.Item;

public class ItemBasic extends Item {

	public ItemBasic(String name) {
		RegisterUtils.RegisterItemName(this, name);
		setMaxDamage(0);
		maxStackSize = 64;
		setCreativeTab(NewBlocks.MyModTab);
	}

}