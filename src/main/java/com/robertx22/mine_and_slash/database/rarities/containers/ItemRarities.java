package com.robertx22.mine_and_slash.database.rarities.containers;

import com.robertx22.mine_and_slash.database.rarities.ItemRarity;
import com.robertx22.mine_and_slash.database.rarities.RaritiesContainer;
import com.robertx22.mine_and_slash.database.rarities.items.*;

import java.util.Arrays;
import java.util.List;

public class ItemRarities extends RaritiesContainer<ItemRarity> {

    public static final List<ItemRarity> Items = Arrays.asList(new CommonItem(), new UncommonItem(), new RareItem(), new EpicItem(), new LegendaryItem(), new MythicalItem());

    @Override
    public List<ItemRarity> rarities() {
        return Items;
    }

    UniqueItem unique = new UniqueItem();

    @Override
    public ItemRarity get(int i) {

        if (i == -1) {
            return unique;
        }

        return super.get(i);

    }

}
