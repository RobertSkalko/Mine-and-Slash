package com.robertx22.mine_and_slash.loot.blueprints;

import com.robertx22.mine_and_slash.database.rarities.RaritiesContainer;
import com.robertx22.mine_and_slash.database.runewords.RuneWord;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.items.misc.ItemAwakenRuneWord;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import net.minecraft.item.ItemStack;

public class AwakenRuneWordBlueprint extends ItemBlueprint {

    public String word = "";

    public AwakenRuneWordBlueprint() {
        super(1);
    }

    public RuneWord getWord() {

        if (SlashRegistry.RuneWords().isRegistered(word) == false) {

            RuneWord random = SlashRegistry.RuneWords().random();

            word = random.GUID();

        }

        return SlashRegistry.RuneWords().get(word);

    }

    @Override
    ItemStack generate() {
        ItemStack stack = new ItemStack(ItemAwakenRuneWord.ITEM);

        ItemAwakenRuneWord item = (ItemAwakenRuneWord) stack.getItem();

        RuneWord word = getWord();

        item.setWord(stack, word);

        return stack;
    }

    @Override
    public RaritiesContainer<? extends Rarity> getRarityContainer() {
        return Rarities.Items;
    }
}
