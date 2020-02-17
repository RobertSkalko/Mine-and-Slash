package com.robertx22.mine_and_slash.loot.blueprints.bases;

import com.robertx22.mine_and_slash.database.runes.base.BaseUniqueRuneItem;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.loot.blueprints.ItemBlueprint;

public class UniqueRunePart extends BlueprintPart<BaseUniqueRuneItem> {

    public UniqueRunePart(ItemBlueprint blueprint) {
        super(blueprint);
    }

    @Override
    protected BaseUniqueRuneItem generateIfNull() {
        return SlashRegistry.UniqueRunes()
                .getWrapped()
                .ofTierOrLess(this.blueprint.tier.number)
                .random();
    }
}
