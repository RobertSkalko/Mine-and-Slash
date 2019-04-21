package com.robertx22.database.gearitemslots;

import java.util.HashMap;

import com.robertx22.customitems.gearitems.offhands.NormalShield;
import com.robertx22.database.gearitemslots.bases.BaseOffHand;

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
