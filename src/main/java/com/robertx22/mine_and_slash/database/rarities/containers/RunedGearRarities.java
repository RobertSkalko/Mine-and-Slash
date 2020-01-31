package com.robertx22.mine_and_slash.database.rarities.containers;

import com.robertx22.mine_and_slash.database.rarities.BaseRaritiesContainer;
import com.robertx22.mine_and_slash.database.rarities.GearRarity;
import com.robertx22.mine_and_slash.database.rarities.gears.*;

public class RunedGearRarities extends BaseRaritiesContainer<GearRarity> {

    public RunedGearRarities() {
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

