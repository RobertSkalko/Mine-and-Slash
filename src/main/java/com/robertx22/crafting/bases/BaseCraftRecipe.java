package com.robertx22.crafting.bases;

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

		ItemStack gear = RecipeUtils.FirstItemIsGear(inv);
		ItemStack currency = RecipeUtils.SecondItemIs(inv, CraftingItem());

		if (gear != null && currency != null) {

			ICurrencyItemEffect orb = (ICurrencyItemEffect) CraftingItem();

			if (orb.CanItemBeModified(gear)) {
				orb.ModifyItem(gear);
				this.output = gear;
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
