package com.robertx22.customitems.currency;

import java.util.List;

import javax.annotation.Nullable;

import com.robertx22.customitems.oldreplacesoon.ItemBasic;
import com.robertx22.datasaving.Saving;
import com.robertx22.mmorpg.Ref;
import com.robertx22.saveclasses.ChaosStatsData;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.utilityclasses.ModelUtils;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@EventBusSubscriber
public class ItemChaosOrb extends ItemBasic implements ICurrencyItemEffect {

	private static final String name = Ref.MODID + ":chaos_orb";

	@GameRegistry.ObjectHolder(Ref.MODID + ":chaos_orb")
	public static final Item ITEM = null;

	public ItemChaosOrb() {

		super(name);

	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		event.getRegistry().register(new ItemChaosOrb());
	}

	@SubscribeEvent
	public static void onModelRegistry(ModelRegistryEvent event) {
		ModelUtils.registerRender(ITEM);
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {

		stack.setStackDisplayName("Chaos Orb");

		tooltip.add("Substance of pure Chaos.");
		tooltip.add("The result can be Good.. or Horrible!");

	}

	@Override
	public void ModifyItem(ItemStack stack) {

		GearItemData gear = Saving.Load(stack);
		gear.chaosStats = new ChaosStatsData();
		gear.chaosStats.setRerollFully = true;
		Saving.Save(stack, gear);

	}

	@Override
	public boolean CanItemBeModified(ItemStack stack) {

		GearItemData gear = Saving.Load(stack);

		if (gear.chaosStats == null) {
			return true;
		}

		return false;
	}

}
