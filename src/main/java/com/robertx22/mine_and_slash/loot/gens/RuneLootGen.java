package com.robertx22.mine_and_slash.loot.gens;

import com.robertx22.mine_and_slash.config.forge.ModConfig;
import com.robertx22.mine_and_slash.loot.LootInfo;
import com.robertx22.mine_and_slash.loot.blueprints.RuneBlueprint;
import com.robertx22.mine_and_slash.uncommon.enumclasses.LootType;
import net.minecraft.item.ItemStack;

public class RuneLootGen extends BaseLootGen<RuneBlueprint> {

    public RuneLootGen(LootInfo info) {
        super(info);
    }

    @Override
    public float baseDropChance() {
        return ModConfig.INSTANCE.DropRates.RUNE_DROPRATE.get().floatValue();
    }

    @Override
    public LootType lootType() {
        return LootType.Rune;
    }

    @Override
    public ItemStack generateOne() {

        RuneBlueprint blueprint = new RuneBlueprint(info.level);

        return blueprint.createStack();

    }

}
