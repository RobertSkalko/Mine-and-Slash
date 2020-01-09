package com.robertx22.mine_and_slash.loot.blueprints.bases;

import com.robertx22.mine_and_slash.database.spells.bases.BaseSpell;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.loot.blueprints.ItemBlueprint;

public class SpellPart extends BlueprintPart<BaseSpell> {

    public SpellPart(ItemBlueprint blueprint) {
        super(blueprint);
    }

    @Override
    protected BaseSpell generateIfNull() {
        return SlashRegistry.Spells().random();
    }
}
