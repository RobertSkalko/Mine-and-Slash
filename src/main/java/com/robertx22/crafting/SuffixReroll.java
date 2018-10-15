package com.robertx22.crafting;

import com.robertx22.datasaving.Saving;
import com.robertx22.mmorpg.Ref;
import com.robertx22.saveclasses.GearItemData;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber
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

	@SubscribeEvent
	public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
		IForgeRegistry<IRecipe> r = event.getRegistry();
		r.register(new SuffixReroll());
		System.out.println("Registering SuffixReroll");
	}

	@Override
	public boolean matches(InventoryCrafting inv, World worldIn) {

		ItemStack gear = RecipeUtils.AnyItemIsGearItem(inv);

		if (gear != null) {

			GearItemData gearitem = Saving.Load(gear);

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
