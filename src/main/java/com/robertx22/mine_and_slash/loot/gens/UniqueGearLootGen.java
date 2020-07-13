package com.robertx22.mine_and_slash.loot.gens;

import com.robertx22.mine_and_slash.config.forge.ModConfig;
import com.robertx22.mine_and_slash.loot.LootInfo;
import com.robertx22.mine_and_slash.loot.blueprints.UniqueGearBlueprint;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.uncommon.enumclasses.LootType;
import net.minecraft.item.ItemStack;

public class UniqueGearLootGen extends BaseLootGen<UniqueGearBlueprint> {

    public UniqueGearLootGen(LootInfo info) {
        super(info);
    }

    @Override
    public float baseDropChance() {
        if (info.world == null) {
            return ModConfig.INSTANCE.DropRates.UNIQUE_DROPRATE.get()
                .floatValue();
        } else {
            return ModConfig.INSTANCE.DropRates.UNIQUE_DROPRATE.get()
                .floatValue() * SlashRegistry.getDimensionConfig(info.world).unique_gear_drop_multi;
        }
    }

    @Override
    public LootType lootType() {
        return LootType.UniqueItem;
    }

    @Override
    public boolean condition() {
        return info.world != null && SlashRegistry.getDimensionConfig(info.world).drops_unique_gear;
    }

    @Override
    public ItemStack generateOne() {

        UniqueGearBlueprint blueprint = new UniqueGearBlueprint(info.tier, true);

        ItemStack stack = blueprint.createStack();

        return stack;

    }

}
