package com.robertx22.mine_and_slash.database.rarities.containers;

import com.robertx22.mine_and_slash.database.rarities.GearRarity;
import com.robertx22.mine_and_slash.database.rarities.RaritiesContainer;
import com.robertx22.mine_and_slash.database.rarities.runed_items.*;

import java.util.Arrays;
import java.util.List;

public class RunedItemRarities extends RaritiesContainer<GearRarity> {

    public static final List<GearRarity> RunedItems = Arrays.asList(new CommonRunedGear(), new UncommonRunedGear(), new RareRunedGear(), new EpicRunedGear(), new LegendaryRunedGear(), new MythicalRunedGear());

    @Override
    public List<GearRarity> rarities() {
        return RunedItems;
    }

    @Override
    public GearRarity unique() {
        return null;
    }

}

