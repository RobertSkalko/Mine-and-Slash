package com.robertx22.mine_and_slash.database.gearitemslots;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.BaseArmor;
import com.robertx22.mine_and_slash.items.gearitems.armor.ItemHelmet;
import net.minecraft.item.Item;

import java.util.HashMap;

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

    @Override
    public String locNameForLangFile() {
        return "Helmet";
    }
}
