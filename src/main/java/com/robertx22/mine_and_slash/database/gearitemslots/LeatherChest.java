package com.robertx22.mine_and_slash.database.gearitemslots;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.LeatherArmor;
import com.robertx22.mine_and_slash.items.gearitems.armor.ItemChest;
import net.minecraft.item.Item;

import java.util.HashMap;

public class LeatherChest extends LeatherArmor {

    @Override
    public Item DefaultItem() {
        return ItemChest.Items.get(0);
    }

    @Override
    public HashMap<Integer, Item> ItemsForRarities() {
        return ItemChest.Items;
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
