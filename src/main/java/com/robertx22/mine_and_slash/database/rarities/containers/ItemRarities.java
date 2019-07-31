package com.robertx22.mine_and_slash.database.rarities.containers;

import com.robertx22.mine_and_slash.database.rarities.ItemRarity;
import com.robertx22.mine_and_slash.database.rarities.RaritiesContainer;
import com.robertx22.mine_and_slash.database.rarities.items.*;

import java.util.Arrays;
import java.util.List;

public class ItemRarities extends RaritiesContainer<ItemRarity> {

    public static final List<ItemRarity> Items = Arrays.asList(new CommonItem(), new UncommonItem(), new RareItem(), new EpicItem(), new LegendaryItem(), new MythicalItem());

    UniqueItem unique = new UniqueItem();

    @Override
    public ItemRarity unique() {
        return unique;
    }

    @Override
    public List<ItemRarity> rarities() {
        return Items;
    }

}
