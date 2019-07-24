package com.robertx22.mine_and_slash.loot.blueprints;

import com.robertx22.mine_and_slash.database.rarities.RaritiesContainer;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.items.runes.base.BaseRuneItem;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;

public class RuneBlueprint extends ItemBlueprint {

    public RuneBlueprint(int level) {
        super(level);

    }

    @Override
    public RaritiesContainer<? extends Rarity> getRarityContainer() {
        return Rarities.Runes;
    }

    BaseRuneItem rune = null;

    public BaseRuneItem getRuneItem() {

        if (rune != null) {
            return rune;
        } else {

            rune = RandomUtils.weightedRandom(SlashRegistry.Runes().getList());

            return rune;

        }

    }

}
