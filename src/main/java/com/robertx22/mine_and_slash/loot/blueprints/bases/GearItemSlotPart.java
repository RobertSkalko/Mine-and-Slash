package com.robertx22.mine_and_slash.loot.blueprints.bases;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;

public class GearItemSlotPart extends BlueprintPart<GearItemSlot> {

    @Override
    protected GearItemSlot generateIfNull() {
        return SlashRegistry.GearTypes().random();
    }
}


