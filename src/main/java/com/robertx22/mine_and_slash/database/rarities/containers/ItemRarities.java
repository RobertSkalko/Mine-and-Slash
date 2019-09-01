package com.robertx22.mine_and_slash.database.rarities.containers;

import com.robertx22.mine_and_slash.database.rarities.GearRarity;
import com.robertx22.mine_and_slash.database.rarities.RaritiesContainer;
import com.robertx22.mine_and_slash.database.rarities.gears.*;

import java.util.Arrays;
import java.util.List;

public class ItemRarities extends RaritiesContainer<GearRarity> {

    public static final List<GearRarity> Items = Arrays.asList(new CommonGear(), new UncommonGear(), new RareGear(), new EpicGear(), new LegendaryGear(), new MythicalGear());

    UniqueGear unique = new UniqueGear();

    @Override
    public GearRarity unique() {
        return unique;
    }

    @Override
    public List<GearRarity> rarities() {
        return Items;
    }

}
