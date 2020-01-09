package com.robertx22.mine_and_slash.loot.gens;

import com.robertx22.mine_and_slash.config.ModConfig;
import com.robertx22.mine_and_slash.loot.LootInfo;
import com.robertx22.mine_and_slash.loot.blueprints.AwakenRuneWordBlueprint;
import com.robertx22.mine_and_slash.uncommon.enumclasses.LootType;
import net.minecraft.item.ItemStack;

public class AwakenRuneWordLootGen extends BaseLootGen<AwakenRuneWordBlueprint> {

    public AwakenRuneWordLootGen(LootInfo info) {
        super(info);
    }

    @Override
    public float baseDropChance() {
        return ModConfig.INSTANCE.DropRates.AWAKEN_RUNEWORD_DROPRATE.get().floatValue();

    }

    @Override
    public LootType lootType() {
        return LootType.AwakenRuneWord;
    }

    @Override
    public boolean condition() {
        return info.level >= ModConfig.INSTANCE.Server.CURRENCY_DROP_AFTER_LEVEL.get();
    }

    @Override
    public ItemStack generateOne() {
        return new AwakenRuneWordBlueprint().createStack();
    }

}
