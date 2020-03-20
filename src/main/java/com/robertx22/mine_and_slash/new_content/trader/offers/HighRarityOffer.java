package com.robertx22.mine_and_slash.new_content.trader.offers;

import com.robertx22.mine_and_slash.loot.LootInfo;
import com.robertx22.mine_and_slash.loot.blueprints.GearBlueprint;
import net.minecraft.item.ItemStack;

public class HighRarityOffer extends TraderOffer {

    @Override
    public ItemStack generateStackInternal(LootInfo info) {
        GearBlueprint blueprint = new GearBlueprint(info);
        blueprint.rarity.minRarity = 2;
        return blueprint.createStack();

    }
}
