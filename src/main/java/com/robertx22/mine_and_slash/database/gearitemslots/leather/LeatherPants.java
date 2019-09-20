package com.robertx22.mine_and_slash.database.gearitemslots.leather;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.LeatherArmor;
import com.robertx22.mine_and_slash.items.gearitems.armor.leather.LeatherPantsItem;
import net.minecraft.item.Item;

import java.util.HashMap;

public class LeatherPants extends LeatherArmor {

    @Override
    public HashMap<Integer, Item> ItemsForRarities() {
        return LeatherPantsItem.Items;
    }

    @Override
    public String locNameForLangFile() {
        return "Leather Pants";
    }

    @Override
    public String GUID() {
        return "Leather_Pants";
    }
}
