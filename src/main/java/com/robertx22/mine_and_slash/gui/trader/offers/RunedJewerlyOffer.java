package com.robertx22.mine_and_slash.gui.trader.offers;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.loot.LootInfo;
import com.robertx22.mine_and_slash.loot.blueprints.RunedGearBlueprint;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import net.minecraft.item.ItemStack;

public class RunedJewerlyOffer extends TraderOffer {
    @Override
    public ItemStack generateStackInternal(LootInfo info) {
        RunedGearBlueprint blueprint = new RunedGearBlueprint(info.level, info.tier);
        blueprint.rarity.minRarity = 3;

        blueprint.gearItemSlot.set(SlashRegistry.GearTypes()
            .getFilterWrapped(x -> x.slotType()
                .equals(GearItemSlot.GearSlotType.Jewerly))
            .random());

        return blueprint.createStack();

    }
}

