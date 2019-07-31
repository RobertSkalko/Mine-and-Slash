package com.robertx22.mine_and_slash.loot.blueprints;

import com.robertx22.mine_and_slash.database.items.runes.base.BaseRuneItem;
import com.robertx22.mine_and_slash.database.rarities.RaritiesContainer;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;

public class UniqueRuneBlueprint extends ItemBlueprint {

    public UniqueRuneBlueprint(int level, int tier) {
        super(level);
        this.tier = tier;

    }

    int tier = 0;

    @Override
    public RaritiesContainer<? extends Rarity> getRarityContainer() {
        return Rarities.Runes;
    }

    public BaseRuneItem getRuneItem() {
        if (this.GUID.isEmpty()) {
            return SlashRegistry.UniqueRunes().getWrapped().ofTierOrLess(tier).random();

        } else {
            return SlashRegistry.UniqueRunes().get(GUID);
        }

    }

}

