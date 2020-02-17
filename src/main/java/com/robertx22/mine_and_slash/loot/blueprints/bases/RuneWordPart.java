package com.robertx22.mine_and_slash.loot.blueprints.bases;

import com.robertx22.mine_and_slash.database.runewords.RuneWord;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.loot.blueprints.ItemBlueprint;

public class RuneWordPart extends BlueprintPart<RuneWord> {

    public RuneWordPart(ItemBlueprint blueprint) {
        super(blueprint);
    }

    @Override
    protected RuneWord generateIfNull() {
        return SlashRegistry.RuneWords().random();
    }
}


