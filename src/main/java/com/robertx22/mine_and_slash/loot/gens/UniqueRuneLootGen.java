package com.robertx22.mine_and_slash.loot.gens;

import com.robertx22.mine_and_slash.config.forge.ModConfig;
import com.robertx22.mine_and_slash.loot.LootInfo;
import com.robertx22.mine_and_slash.loot.blueprints.UniqueRuneBlueprint;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.uncommon.enumclasses.LootType;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.WorldUtils;
import net.minecraft.item.ItemStack;

public class UniqueRuneLootGen extends BaseLootGen<UniqueRuneBlueprint> {

    public UniqueRuneLootGen(LootInfo info) {
        super(info);
    }

    @Override
    public float baseDropChance() {
        return ModConfig.INSTANCE.DropRates.UNIQUE_RUNE_DROPRATE.get()
            .floatValue();
    }

    @Override
    public boolean condition() {
        return WorldUtils.dropsUniques(info.world) && SlashRegistry.Runes()
            .getFiltered(x -> x.isUnique)
            .stream()
            .anyMatch(x -> info.tier >= x.Tier());

    }

    @Override
    public LootType lootType() {
        return LootType.UniqueRune;
    }

    @Override
    public ItemStack generateOne() {

        UniqueRuneBlueprint blueprint = new UniqueRuneBlueprint(info.level, info.tier);

        return blueprint.createStack();

    }

}
