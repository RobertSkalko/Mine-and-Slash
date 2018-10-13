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

	public boolean AnyItemIsGearItem(InventoryCrafting inv) {

		for (int i = 0; i < inv.getSizeInventory(); i++) {
			ItemStack stack = inv.getStackInSlot(i);

			if (Saving.Load(stack.getTagCompound(), GearItemData.class) != null) {
				gearitem = stack;
				output = gearitem;
				return true;
			}

		}

		return false;
	}

	ItemStack gearitem = null;

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

		return AnyItemIsGearItem(inv);
	}

	ItemStack output = null;

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
