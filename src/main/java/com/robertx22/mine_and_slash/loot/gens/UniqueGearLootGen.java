package com.robertx22.mine_and_slash.loot.gens;

import com.robertx22.mine_and_slash.config.forge.ModConfig;
import com.robertx22.mine_and_slash.loot.LootInfo;
import com.robertx22.mine_and_slash.loot.blueprints.UniqueGearBlueprint;
import com.robertx22.mine_and_slash.uncommon.enumclasses.LootType;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.WorldUtils;
import net.minecraft.item.ItemStack;

public class UniqueGearLootGen extends BaseLootGen<UniqueGearBlueprint> {

    public UniqueGearLootGen(LootInfo info) {
        super(info);
    }

    @Override
    public float baseDropChance() {
        return ModConfig.INSTANCE.DropRates.UNIQUE_DROPRATE.get()
            .floatValue();
    }

    @Override
    public LootType lootType() {
        return LootType.UniqueItem;
    }

    @Override
    public boolean condition() {
        return WorldUtils.dropsUniques(info.world);
    }

    @Override
    public ItemStack generateOne() {

        UniqueGearBlueprint blueprint = new UniqueGearBlueprint(info.tier, true);

        ItemStack stack = blueprint.createStack();

        return stack;

    }

}
