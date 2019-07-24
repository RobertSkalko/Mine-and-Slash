package com.robertx22.mine_and_slash.uncommon.utilityclasses;

import net.minecraft.item.Item;

public class RegisterItemUtils {

    public static void RegisterItemName(Item item, String name) {
	if (item.getRegistryName() == null) {
	    item.setRegistryName(name);
	}
    }
}