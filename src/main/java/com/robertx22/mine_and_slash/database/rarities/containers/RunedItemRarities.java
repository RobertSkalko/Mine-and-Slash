package com.robertx22.mine_and_slash.database.rarities.containers;

import com.robertx22.mine_and_slash.database.rarities.GearRarity;
import com.robertx22.mine_and_slash.database.rarities.RaritiesContainer;
import com.robertx22.mine_and_slash.database.rarities.gears.*;

public class RunedItemRarities extends RaritiesContainer<GearRarity> {

    public RunedItemRarities() {
        super();
        add(CommonRunedGear.getInstance());
        add(UncommonRunedGear.getInstance());
        add(RareRunedGear.getInstance());
        add(EpicRunedGear.getInstance());
        add(LegendaryRunedGear.getInstance());
        add(MythicalRunedGear.getInstance());

        this.onInit();
    }

}

