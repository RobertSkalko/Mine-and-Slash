package com.robertx22.mine_and_slash.database.gearitemslots.leather;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.LeatherArmor;
import com.robertx22.mine_and_slash.items.gearitems.armor.leather.LeatherBootsItem;
import net.minecraft.item.Item;

import java.util.HashMap;

public class LeatherBoots extends LeatherArmor {

    @Override
    public HashMap<Integer, Item> ItemsForRarities() {
        return LeatherBootsItem.Items;
    }

    @Override
    public String locNameForLangFile() {
        return "Leather Boots";
    }

    @Override
    public String GUID() {
        return "Leather_Boots";
    }
}
