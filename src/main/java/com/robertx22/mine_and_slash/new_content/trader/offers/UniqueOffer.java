package com.robertx22.mine_and_slash.new_content.trader.offers;

import com.robertx22.mine_and_slash.loot.LootInfo;
import com.robertx22.mine_and_slash.loot.blueprints.UniqueGearBlueprint;
import net.minecraft.item.ItemStack;

public class UniqueOffer extends TraderOffer {
    public UniqueOffer() {
        this.amount = 3;
    }

    @Override
    public ItemStack generateStackInternal(LootInfo info) {
        UniqueGearBlueprint blueprint = new UniqueGearBlueprint(info.level, info.tier);
        return blueprint.createStack();

    }

    @Override
    public int Weight() {
        return 75;
    }
}
