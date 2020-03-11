package com.robertx22.mine_and_slash.new_content.trader.offers;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.loot.LootInfo;
import com.robertx22.mine_and_slash.loot.blueprints.GearBlueprint;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import net.minecraft.item.ItemStack;

public class PlayStyleOffer extends TraderOffer {

    GearItemSlot.PlayStyle style;

    public PlayStyleOffer(GearItemSlot.PlayStyle style) {
        this.style = style;
    }

    @Override
    public ItemStack generateStackInternal(LootInfo info) {
        GearBlueprint blueprint = new GearBlueprint(info);
        blueprint.rarity.minRarity = 2;

        blueprint.gearItemSlot.set(SlashRegistry.GearTypes()
            .getFilterWrapped(x -> x.getPlayStyle()
                .equals(style))
            .random());

        return blueprint.createStack();

    }
}
