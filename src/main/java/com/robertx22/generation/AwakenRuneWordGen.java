package com.robertx22.generation;

import com.robertx22.database.runewords.RuneWord;
import com.robertx22.generation.blueprints.AwakenRuneWordBlueprint;
import com.robertx22.items.misc.ItemAwakenRuneWord;

import net.minecraft.item.ItemStack;

public class AwakenRuneWordGen {

    public static ItemStack Create(AwakenRuneWordBlueprint blueprint) {

	ItemStack stack = new ItemStack(ItemAwakenRuneWord.ITEM);

	ItemAwakenRuneWord item = (ItemAwakenRuneWord) stack.getItem();

	RuneWord word = blueprint.getWord();

	item.setWord(stack, word);

	return stack;

    }
}
