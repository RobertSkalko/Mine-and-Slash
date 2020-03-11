package com.robertx22.mine_and_slash.gui.trader.offers;

import com.robertx22.mine_and_slash.loot.LootInfo;
import com.robertx22.mine_and_slash.loot.blueprints.RunedGearBlueprint;
import net.minecraft.item.ItemStack;

public class HighRarityRunedOffer extends TraderOffer {
    @Override
    public ItemStack generateStackInternal(LootInfo info) {
        RunedGearBlueprint blueprint = new RunedGearBlueprint(info.level, info.tier);
        blueprint.rarity.minRarity = 3;
        return blueprint.createStack();

    }
}
