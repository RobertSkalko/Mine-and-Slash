package com.robertx22.utilityclasses;

import com.robertx22.customitems.oldreplacesoon.MyItems;

import net.minecraft.item.Item;

public class ItemUtils {

	public static boolean isPowder(Item item) {

		if (item.equals(MyItems.magic_powder) || item.equals(MyItems.rare_powder) || item.equals(MyItems.epic_powder)
				|| item.equals(MyItems.legendary_powder) || item.equals(MyItems.mythical_powder)) {
			return true;
		}
		return false;

	}

	public static boolean isOre(Item item) {

		if (item.equals(MyItems.magic_ore) || item.equals(MyItems.rare_ore) || item.equals(MyItems.epic_ore)
				|| item.equals(MyItems.legendary_ore) || item.equals(MyItems.mythical_ore)) {
			return true;
		}
		return false;

	}

	public static boolean isSocket(Item item) {

		if (item.equals(MyItems.magic_socket) || item.equals(MyItems.rare_socket) || item.equals(MyItems.epic_socket)
				|| item.equals(MyItems.legendary_socket) || item.equals(MyItems.mythical_socket)) {
			return true;
		}
		return false;

	}

}
