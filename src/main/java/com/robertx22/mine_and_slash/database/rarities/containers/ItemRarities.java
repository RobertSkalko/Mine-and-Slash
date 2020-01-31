package com.robertx22.mine_and_slash.database.rarities.containers;

import com.robertx22.mine_and_slash.database.rarities.GearRarity;
import com.robertx22.mine_and_slash.database.rarities.RaritiesContainer;
import com.robertx22.mine_and_slash.database.rarities.gears.*;

public class ItemRarities extends RaritiesContainer<GearRarity> {

    public ItemRarities() {
        super();
        add(CommonGear.getInstance());
        add(UncommonGear.getInstance());
        add(RareGear.getInstance());
        add(EpicGear.getInstance());
        add(LegendaryGear.getInstance());
        add(MythicalGear.getInstance());
        add(UniqueGear.getInstance());

        this.onInit();
    }

}
