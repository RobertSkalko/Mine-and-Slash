package com.robertx22.crafting.bases;

import java.util.Arrays;

import com.robertx22.crafting.RecipeUtils;
import com.robertx22.customitems.currency.ICurrencyItemEffect;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

public abstract class BaseCraftRecipe implements IRecipe, IRecipeOutput {

	ItemStack output = null;

	public abstract Item CraftingItem();

	@Override
	public boolean matches(InventoryCrafting inv, World worldIn) {

		if (RecipeUtils.SecondItemIs(inv, CraftingItem()) == null
				|| RecipeUtils.AreAllOtherSlotsEmpty(inv, Arrays.asList(0, 1))) {
			return false;
		}
		ItemStack gear = RecipeUtils.FirstItemIsGear(inv);

		if (gear != null) {

			ICurrencyItemEffect orb = (ICurrencyItemEffect) CraftingItem();

			ItemStack copy = gear.copy();

			if (orb.CanItemBeModified(copy)) {
				orb.ModifyItem(copy);
				this.output = copy;
				return true;
			}

		}

		return false;

	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting inv) {

		return GetFinalOutput();

	}

	@Override
	public boolean canFit(int width, int height) {
		return width > 1 && height > 1;
	}

	@Override
	public ItemStack getRecipeOutput() {

		return GetFinalOutput();
	}

	@Override
	public ItemStack OutputStack() {
		return output;
	}

}
