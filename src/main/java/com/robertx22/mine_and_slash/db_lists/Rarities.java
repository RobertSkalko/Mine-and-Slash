package com.robertx22.mine_and_slash.db_lists;

import com.robertx22.mine_and_slash.database.rarities.GearRarity;
import com.robertx22.mine_and_slash.database.rarities.containers.*;
import com.robertx22.mine_and_slash.database.rarities.gears.UniqueGear;

import java.util.ArrayList;
import java.util.List;

public class Rarities {

    public static final int MAXIMUM_ITEM_RARITY = 5;

    public static final RunedItemRarities RunedItems = new RunedItemRarities();
    public static final RuneRarities Runes = new RuneRarities();
    public static final ItemRarities Items = new ItemRarities();
    public static final MapRarities Maps = new MapRarities();
    public static final MobRarities Mobs = new MobRarities();

    public static List<GearRarity> allIncludingUnique() {
        List<GearRarity> list = new ArrayList<>(Items.rarities());
        list.add(new UniqueGear());
        return list;
    }

}
