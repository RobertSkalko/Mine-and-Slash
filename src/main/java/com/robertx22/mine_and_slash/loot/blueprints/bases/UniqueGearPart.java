package com.robertx22.mine_and_slash.loot.blueprints.bases;

import com.robertx22.mine_and_slash.database.items.unique_items.IUnique;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.loot.blueprints.GearBlueprint;
import com.robertx22.mine_and_slash.loot.blueprints.ItemBlueprint;

public class UniqueGearPart extends BlueprintPart<IUnique> {

    public UniqueGearPart(ItemBlueprint blueprint) {
        super(blueprint);
    }

    @Override
    protected IUnique generateIfNull() {

        if (blueprint.tier.isRandom == false) {
            return SlashRegistry.UniqueGears()
                    .getWrapped()
                    .ofExactTier(blueprint.tier.number)
                    .random();

        } else {
            return randomUnique();
        }

    }

    private IUnique randomUnique() {

        GearBlueprint gearBlueprint = (GearBlueprint) blueprint;

        return SlashRegistry.UniqueGears()
                .getWrapped()
                .ofTierOrLess(blueprint.tier.number)
                .ofSpecificGearType(gearBlueprint.gearItemSlot.get().GUID())
                .random();

    }

}
