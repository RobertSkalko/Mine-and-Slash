package com.robertx22.database.gearitemslots;

import java.util.HashMap;

import com.robertx22.customitems.gearitems.weapons.ItemBow;
import com.robertx22.database.gearitemslots.bases.BaseWeapon;

import net.minecraft.item.Item;

public class Bow extends BaseWeapon {

	@Override
	public String Name() {
		return "Bow";
	}

	@Override
	public Item DefaultItem() {
		return ItemBow.Items.get(0);
	}

	@Override
	public HashMap<Integer, Item> ItemsForRarities() {
		return ItemBow.Items;
	}

}
