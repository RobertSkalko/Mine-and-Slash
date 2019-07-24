package com.robertx22.mine_and_slash.database.rarities.containers;

import com.robertx22.mine_and_slash.database.rarities.RaritiesContainer;
import com.robertx22.mine_and_slash.database.rarities.SpellRarity;
import com.robertx22.mine_and_slash.database.rarities.spells.*;

import java.util.Arrays;
import java.util.List;

public class SpellRarities extends RaritiesContainer<SpellRarity> {

    public static final List<SpellRarity> Spells = Arrays.asList(new CommonSpell(), new UncommonSpell(), new RareSpell(), new EpicSpell(), new LegendarySpell(), new MythicalSpell());

    @Override
    public List<SpellRarity> rarities() {
        return Spells;
    }
}
