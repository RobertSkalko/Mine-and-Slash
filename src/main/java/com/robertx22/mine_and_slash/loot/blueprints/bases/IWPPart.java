package com.robertx22.mine_and_slash.loot.blueprints.bases;

import com.robertx22.mine_and_slash.database.world_providers.base.IWP;
import com.robertx22.mine_and_slash.loot.blueprints.MapBlueprint;
import com.robertx22.mine_and_slash.registry.SlashRegistry;

public class IWPPart extends BlueprintPart<IWP> {

    public IWPPart(MapBlueprint blueprint) {
        super(blueprint);
    }

    @Override
    protected IWP generateIfNull() {
        return SlashRegistry.WorldProviders()
            .random();
    }
}
