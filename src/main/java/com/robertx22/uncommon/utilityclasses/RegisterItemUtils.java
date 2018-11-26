package com.robertx22.uncommon.utilityclasses;

import com.robertx22.mmorpg.Ref;

import net.minecraft.item.Item;

public class RegisterItemUtils {

	public static void RegisterItemName(Item item, String name) {
		if (item.getRegistryName() == null) {
			item.setRegistryName(name);
			item.setUnlocalizedName(Ref.MODID + ":" + name.toLowerCase());
		}
	}
}