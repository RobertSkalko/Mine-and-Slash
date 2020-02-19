package com.robertx22.mine_and_slash.loot.blueprints.bases;

import com.robertx22.mine_and_slash.database.runes.base.BaseRune;
import com.robertx22.mine_and_slash.loot.blueprints.ItemBlueprint;
import com.robertx22.mine_and_slash.registry.SlashRegistry;

public class UniqueRunePart extends BlueprintPart<BaseRune> {

    public UniqueRunePart(ItemBlueprint blueprint) {
        super(blueprint);
    }

    @Override
    protected BaseRune generateIfNull() {
        return SlashRegistry.Runes()
            .getFilterWrapped(x -> x.isUnique())
            .ofTierOrLess(this.blueprint.tier.number)
            .random();
    }
}
