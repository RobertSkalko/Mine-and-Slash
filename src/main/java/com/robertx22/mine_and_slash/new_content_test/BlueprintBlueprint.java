package com.robertx22.mine_and_slash.new_content_test;

import com.robertx22.mine_and_slash.database.rarities.RaritiesContainer;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.loot.blueprints.ItemBlueprint;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;

public class BlueprintBlueprint extends ItemBlueprint {

    public int tier = 0;

    public BlueprintBlueprint(int level, int tier) {
        super(level);
        this.tier = tier;
    }

    @Override
    public RaritiesContainer<? extends Rarity> getRarityContainer() {
        return Rarities.Items;
    }
}
