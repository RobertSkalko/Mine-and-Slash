package com.robertx22.database.gearitemslots;

import java.util.HashMap;

import com.robertx22.database.gearitemslots.bases.BaseArmor;
import com.robertx22.items.gearitems.armor.ItemHelmet;

import net.minecraft.item.Item;

public class Helmet extends BaseArmor {

    @Override
    public String GUID() {
	return "Helmet";
    }

    @Override
    public Item DefaultItem() {
	return ItemHelmet.Items.get(0);
    }

    @Override
    public HashMap<Integer, Item> ItemsForRarities() {
	return ItemHelmet.Items;
    }

}
