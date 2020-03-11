package com.robertx22.mine_and_slash.gui.trader.offers;

import com.robertx22.mine_and_slash.loot.LootInfo;
import com.robertx22.mine_and_slash.loot.blueprints.GearBlueprint;
import net.minecraft.item.ItemStack;

public class ChaosStatsOffer extends TraderOffer {

    @Override
    public ItemStack generateStackInternal(LootInfo info) {
        GearBlueprint blueprint = new GearBlueprint(info);
        blueprint.rarity.minRarity = 3;
        blueprint.chaosStatChance = 100;
        return blueprint.createStack();

    }
}
