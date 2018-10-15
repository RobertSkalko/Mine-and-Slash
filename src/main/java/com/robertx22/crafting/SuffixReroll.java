package com.robertx22.crafting;

import com.robertx22.mmorpg.Ref;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.saving.Saving;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class SuffixReroll implements IRecipe, IRecipeOutput {

	ItemStack output = null;

	@Override
	public IRecipe setRegistryName(ResourceLocation name) {
		return this;
	}

	@Override
	public ResourceLocation getRegistryName() {
		return new ResourceLocation(Ref.MODID + ":SuffixReroll");

	}

	@Override
	public Class<IRecipe> getRegistryType() {
		return this.getRegistryType();
	}

	@Override
	public boolean matches(InventoryCrafting inv, World worldIn) {

		if (!worldIn.isRemote) {

			ItemStack gear = RecipeUtils.AnyItemIsGearItem(inv);

			if (gear != null) {

				GearItemData gearitem = Saving.Load(gear, GearItemData.class);

				if (gearitem.suffix != null) {
					gearitem.suffix.setRerollFully = true;
				} else {
					return false;
				}
				Saving.Save(gear, gearitem);

				this.output = gear;
			}

			return gear != null;
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
