package com.robertx22.database.gearitemslots;

import java.util.HashMap;

import com.robertx22.customitems.armor.ItemBoots;
import com.robertx22.database.gearitemslots.bases.BaseArmor;

import net.minecraft.item.Item;

public class Boots extends BaseArmor {

	@Override
	public String Name() {
		return "Boots";
	}

	@Override
	public Item DefaultItem() {
		return ItemBoots.Items.get(0);
	}

	@Override
	public HashMap<Integer, Item> ItemsForRarities() {
		return ItemBoots.Items;
	}

}
