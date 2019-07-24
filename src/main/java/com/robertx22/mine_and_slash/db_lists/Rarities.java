package com.robertx22.mine_and_slash.db_lists;

import com.robertx22.mine_and_slash.database.rarities.ItemRarity;
import com.robertx22.mine_and_slash.database.rarities.containers.*;
import com.robertx22.mine_and_slash.database.rarities.items.UniqueItem;

import java.util.ArrayList;
import java.util.List;

public class Rarities {

    public static final int MAXIMUM_ITEM_RARITY = 5;

    public static final RunedItemRarities RunedItems = new RunedItemRarities();
    public static final RuneRarities Runes = new RuneRarities();
    public static final ItemRarities Items = new ItemRarities();
    public static final MapRarities Maps = new MapRarities();
    public static final MobRarities Mobs = new MobRarities();
    public static final SpellRarities Spells = new SpellRarities();

    public static List<ItemRarity> allIncludingUnique() {
        List<ItemRarity> list = new ArrayList<>(Items.rarities());
        list.add(new UniqueItem());
        return list;
    }

}
