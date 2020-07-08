package com.robertx22.mine_and_slash.database.rarities.containers;

import com.robertx22.mine_and_slash.database.rarities.BaseRaritiesContainer;
import com.robertx22.mine_and_slash.database.rarities.GearRarity;
import com.robertx22.mine_and_slash.database.rarities.RarityTypeEnum;
import com.robertx22.mine_and_slash.database.rarities.gears.CommonGear;
import com.robertx22.mine_and_slash.database.rarities.gears.RareGear;
import com.robertx22.mine_and_slash.database.rarities.gears.UncommonGear;
import com.robertx22.mine_and_slash.database.rarities.gears.UniqueGear;

public class GearRarities extends BaseRaritiesContainer<GearRarity> {

    public GearRarities() {
        super();
        add(CommonGear.getInstance());
        add(UncommonGear.getInstance());
        add(RareGear.getInstance());
        add(UniqueGear.getInstance());

        this.onInit();
    }

    @Override
    public RarityTypeEnum getType() {
        return RarityTypeEnum.GEAR;
    }

}
