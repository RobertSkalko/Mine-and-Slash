package com.robertx22.mine_and_slash.database.gearitemslots.leather;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.LeatherArmor;
import com.robertx22.mine_and_slash.items.gearitems.armor.leather.LeatherChestItem;
import net.minecraft.item.Item;

import java.util.HashMap;

public class LeatherChest extends LeatherArmor {

    @Override
    public HashMap<Integer, Item> ItemsForRarities() {
        return LeatherChestItem.Items;
    }

    @Override
    public String locNameForLangFile() {
        return "Leather Chest";
    }

    @Override
    public String GUID() {
        return "Leather_Chest";
    }
}
