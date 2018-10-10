package com.robertx22.item;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.List;
import java.util.Random;

import com.robertx22.constants.Tags;
import com.robertx22.customitems.MyItems;

public class SocketCreator {

	static public ItemStack createSocket(int lvl, int rarity, String gearType) {

		ItemStack item = new ItemStack(MyItems.magic_socket);

		if (rarity == 1) {
			item = new ItemStack(MyItems.rare_socket);
		}
		if (rarity == 2) {
			item = new ItemStack(MyItems.epic_socket);
		}
		if (rarity == 3) {
			item = new ItemStack(MyItems.legendary_socket);
		}
		if (rarity == 4) {
			item = new ItemStack(MyItems.mythical_socket);
		}

		return item;
	}
}
