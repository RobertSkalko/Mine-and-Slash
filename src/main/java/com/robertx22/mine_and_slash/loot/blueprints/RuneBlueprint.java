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

    public BaseRuneItem getRuneItem() {

        if (this.GUID.isEmpty()) {
            return RandomUtils.weightedRandom(SlashRegistry.Runes().getList());

        } else {
            return SlashRegistry.Runes().get(GUID);
        }

    }

}
