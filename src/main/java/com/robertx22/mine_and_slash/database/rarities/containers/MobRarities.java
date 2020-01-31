package com.robertx22.mine_and_slash.database.rarities.containers;

import com.robertx22.mine_and_slash.database.rarities.MobRarity;
import com.robertx22.mine_and_slash.database.rarities.RaritiesContainer;
import com.robertx22.mine_and_slash.database.rarities.mobs.*;

public class MobRarities extends RaritiesContainer<MobRarity> {

    public MobRarities() {
        super();
        add(CommonMob.getInstance());
        add(UncommonMob.getInstance());
        add(RareMob.getInstance());
        add(EpicMob.getInstance());
        add(LegendaryMob.getInstance());
        add(MythicalMob.getInstance());
        add(BossMobRarity.getInstance());

        this.onInit();
    }

}


