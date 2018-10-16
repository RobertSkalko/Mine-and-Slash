package com.robertx22.crafting.currency;

import com.robertx22.crafting.bases.BaseCraftRecipe;
import com.robertx22.customitems.currency.ItemChaosOrb;
import com.robertx22.mmorpg.Ref;

import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class RecipeChaosOrb extends BaseCraftRecipe {

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
		event.getRegistry().register(new RecipeChaosOrb());
	}

	@Override
	public Item CraftingItem() {
		return new ItemChaosOrb();
	}
}
