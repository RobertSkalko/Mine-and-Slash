package com.robertx22.mine_and_slash.loot.blueprints.bases;

import com.robertx22.mine_and_slash.database.items.runes.base.BaseRuneItem;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.loot.blueprints.ItemBlueprint;

public class RunePart extends BlueprintPart<BaseRuneItem> {

    public RunePart(ItemBlueprint blueprint) {
        super(blueprint);
    }

    @Override
    protected BaseRuneItem generateIfNull() {
        return SlashRegistry.Runes().random();
    }
}
