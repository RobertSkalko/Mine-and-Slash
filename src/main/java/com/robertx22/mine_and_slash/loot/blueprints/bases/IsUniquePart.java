package com.robertx22.mine_and_slash.loot.blueprints.bases;

import com.robertx22.mine_and_slash.config.forge.ModConfig;
import com.robertx22.mine_and_slash.database.unique_items.IUnique;
import com.robertx22.mine_and_slash.loot.LootInfo;
import com.robertx22.mine_and_slash.loot.blueprints.GearBlueprint;
import com.robertx22.mine_and_slash.registry.FilterListWrap;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;

public class IsUniquePart extends BlueprintPart<Boolean> {

    public IsUniquePart(GearBlueprint blueprint) {
        super(blueprint);
    }

    public float chance = -1;

    boolean customSetup = false;

    @Override
    protected Boolean generateIfNull() {

        if (!customSetup) {
            try {
                throw new Exception("Didn't setup unique part!!!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (chance == -1) {
            setup(null);
        }

        return RandomUtils.roll(chance);
    }

    public void setup(LootInfo info) {
        customSetup = true;

        if (info != null) {
            chance = ModConfig.INSTANCE.DropRates.CHANCE_FOR_ITEM_TO_BECOME_UNIQUE.get()
                .floatValue();

            if (info.isChestLoot) {
                chance *= 2;
            }

            if (info.world == null || !SlashRegistry.getDimensionConfig(info.world).drops_unique_gear) {
                chance = 0;
            }

            if (!canMakeUnique((GearBlueprint) blueprint)) {
                chance = 0;
            }

        } else {
            chance = 0;
        }

    }

    private static boolean canMakeUnique(GearBlueprint blueprint) {
        FilterListWrap<IUnique> wrap = SlashRegistry.UniqueGears()
            .getWrapped()
            .ofTierOrLess(blueprint.tier.get());

        wrap = wrap.ofSpecificGearType(blueprint.gearItemSlot.get()
            .GUID());

        return !wrap.list.isEmpty();

    }
}
