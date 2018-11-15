package com.robertx22.customitems.oldreplacesoon;

import com.robertx22.database.lists.CreativeTabList;
import com.robertx22.uncommon.utilityclasses.RegisterItemUtils;

import net.minecraft.item.Item;

public class ItemBasic extends Item {

	public ItemBasic(String name) {
		RegisterItemUtils.RegisterItemName(this, name);
		setMaxDamage(0);
		maxStackSize = 64;
		setCreativeTab(CreativeTabList.MyModTab);
	}

}