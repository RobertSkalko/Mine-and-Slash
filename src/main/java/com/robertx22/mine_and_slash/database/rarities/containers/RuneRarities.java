package com.robertx22.mine_and_slash.database.rarities.containers;

import com.robertx22.mine_and_slash.database.rarities.RaritiesContainer;
import com.robertx22.mine_and_slash.database.rarities.RuneRarity;
import com.robertx22.mine_and_slash.database.rarities.runes.*;

import java.util.Arrays;
import java.util.List;

public class RuneRarities extends RaritiesContainer<RuneRarity> {

    private static final List<RuneRarity> Runes = Arrays.asList(new CommonRune(), new UncommonRune(), new RareRune(), new EpicRune(), new LegendaryRune(), new MythicalRune());

    UniqueRune unique = new UniqueRune();

    @Override
    public List<RuneRarity> rarities() {
        return Runes;
    }

    @Override
    public RuneRarity unique() {
        return unique;
    }

}
