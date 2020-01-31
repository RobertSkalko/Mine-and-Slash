package com.robertx22.mine_and_slash.database.rarities.containers;

import com.robertx22.mine_and_slash.database.rarities.RaritiesContainer;
import com.robertx22.mine_and_slash.database.rarities.RuneRarity;
import com.robertx22.mine_and_slash.database.rarities.runes.*;

public class RuneRarities extends RaritiesContainer<RuneRarity> {

    public RuneRarities() {
        super();
        add(CommonRune.getInstance());
        add(UncommonRune.getInstance());
        add(RareRune.getInstance());
        add(EpicRune.getInstance());
        add(LegendaryRune.getInstance());
        add(MythicalRune.getInstance());
        add(UniqueRune.getInstance());

        this.onInit();
    }

}
