package com.robertx22.mine_and_slash.database.rarities.containers;

import com.robertx22.mine_and_slash.database.rarities.MobRarity;
import com.robertx22.mine_and_slash.database.rarities.RaritiesContainer;
import com.robertx22.mine_and_slash.database.rarities.mobs.*;

import java.util.Arrays;
import java.util.List;

public class MobRarities extends RaritiesContainer<MobRarity> {
    public static final List<MobRarity> Mobs = Arrays.asList(new CommonMob(), new UncommonMob(), new RareMob(), new EpicMob(), new LegendaryMob(), new MythicalMob());

    @Override
    public List<MobRarity> rarities() {
        return Mobs;
    }
}

