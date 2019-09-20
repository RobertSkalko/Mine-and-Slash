package com.robertx22.mine_and_slash.database.gearitemslots.leather;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.LeatherArmor;
import com.robertx22.mine_and_slash.items.gearitems.armor.leather.LeatherHelmetItem;
import net.minecraft.item.Item;

import java.util.HashMap;

public class LeatherHelmet extends LeatherArmor {

    @Override
    public HashMap<Integer, Item> ItemsForRarities() {
        return LeatherHelmetItem.Items;
    }

    @Override
    public String locNameForLangFile() {
        return "Leather Helmet";
    }

    @Override
    public String GUID() {
        return "Leather_Helmet";
    }
}
