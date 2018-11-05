package com.robertx22.database.gearitemslots;

import java.util.HashMap;

import com.robertx22.customitems.weapons.ItemSword;
import com.robertx22.database.gearitemslots.bases.BaseWeapon;

import net.minecraft.item.Item;

public class Sword extends BaseWeapon {

	@Override
	public String Name() {
		return "Sword";
	}

	@Override
	public Item DefaultItem() {
		return ItemSword.Items.get(0);
	}

	@Override
	public HashMap<Integer, Item> ItemsForRarities() {
		return ItemSword.Items;
	}

	@Override
	public int Weight() {
		return 1500;
	}

}
