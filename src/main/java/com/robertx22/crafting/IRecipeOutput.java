package com.robertx22.crafting;

import net.minecraft.item.ItemStack;

public interface IRecipeOutput {

	public abstract ItemStack OutputStack();

	public default ItemStack GetFinalOutput() {

		return OutputStack() != null ? OutputStack().copy() : ItemStack.EMPTY;
	}

}
