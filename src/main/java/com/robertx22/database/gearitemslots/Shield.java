package com.robertx22.database.gearitemslots;

import java.util.HashMap;

import com.robertx22.database.gearitemslots.bases.BaseOffHand;
import com.robertx22.items.gearitems.offhands.NormalShield;

import net.minecraft.item.Item;

public class Shield extends BaseOffHand {

    @Override
    public String GUID() {
	return "Shield";
    }

    @Override
    public Item DefaultItem() {
	return NormalShield.Items.get(0);
    }

    @Override
    public HashMap<Integer, Item> ItemsForRarities() {
	return NormalShield.Items;
    }

}
