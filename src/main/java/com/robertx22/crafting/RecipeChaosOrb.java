package com.robertx22.crafting;

import com.robertx22.customitems.currency.ItemChaosOrb;
import com.robertx22.mmorpg.Ref;

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
public class RecipeChaosOrb implements IRecipe, IRecipeOutput {

	ItemStack output = null;

	@Override
	public IRecipe setRegistryName(ResourceLocation name) {
		return this;
	}

	@Override
	public ResourceLocation getRegistryName() {
		return new ResourceLocation(Ref.MODID + ":chaos_orb");

	}

	@Override
	public Class<IRecipe> getRegistryType() {
		return this.getRegistryType();
	}

	@SubscribeEvent
	public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
		IForgeRegistry<IRecipe> r = event.getRegistry();
		r.register(new RecipeChaosOrb());
	}

	@Override
	public boolean matches(InventoryCrafting inv, World worldIn) {

		ItemStack gear = RecipeUtils.FirstItemIsGear(inv);
		ItemStack currency = RecipeUtils.SecondItemIs(inv, new ItemChaosOrb());

		if (gear != null && currency != null) {

			ItemChaosOrb orb = new ItemChaosOrb();

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
