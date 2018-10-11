package com.robertx22.item;

import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;

import com.robertx22.constants.Gear;

public class GearCreator {

	public static ItemStack createGear(int lvl, int rarity, String type) {

		return null;
	}

	ItemStack setName(ItemStack item, int rarityNum) {

		item = item.setStackDisplayName(
				Gear.rarityChatColors[rarityNum] + "" + TextFormatting.BOLD + item.getDisplayName());

		return item;
	}

}
