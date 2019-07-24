package com.robertx22.mine_and_slash.loot.gens;

import com.robertx22.mine_and_slash.config.ModConfig;
import com.robertx22.mine_and_slash.database.runewords.RuneWord;
import com.robertx22.mine_and_slash.items.misc.ItemAwakenRuneWord;
import com.robertx22.mine_and_slash.loot.LootInfo;
import com.robertx22.mine_and_slash.loot.blueprints.AwakenRuneWordBlueprint;
import com.robertx22.mine_and_slash.uncommon.enumclasses.LootType;
import net.minecraft.item.ItemStack;

public class AwakenRuneWordLootGen extends BaseLootGen {

    public AwakenRuneWordLootGen(LootInfo info) {
        super(info);
    }

    @Override
    public float BaseChance() {
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

        return Create(new AwakenRuneWordBlueprint());

    }

    public static ItemStack Create(AwakenRuneWordBlueprint blueprint) {

        ItemStack stack = new ItemStack(ItemAwakenRuneWord.ITEM);

        ItemAwakenRuneWord item = (ItemAwakenRuneWord) stack.getItem();

        RuneWord word = blueprint.getWord();

        item.setWord(stack, word);

        return stack;

    }

}
