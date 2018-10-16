package com.robertx22.customitems.currency;

import java.util.List;

import javax.annotation.Nullable;

import com.robertx22.customitems.oldreplacesoon.ItemBasic;
import com.robertx22.mmorpg.Ref;
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
public class ItemRandomizeSuffix extends ItemBasic implements ICurrencyItemEffect {

	private static final String name = Ref.MODID + ":randomize_suffix";

	@GameRegistry.ObjectHolder(Ref.MODID + ":randomize_suffix")
	public static final Item ITEM = null;

	public ItemRandomizeSuffix() {

		super(name);

	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		event.getRegistry().register(new ItemRandomizeSuffix());
	}

	@SubscribeEvent
	public static void onModelRegistry(ModelRegistryEvent event) {
		ModelUtils.registerRender(ITEM);
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {

		stack.setStackDisplayName("Orb Of Ever-Changing Suffix");

		tooltip.add("Fully randomizes the Suffix of an Item.");

	}

	@Override
	public void ModifyItem(ItemStack stack) {

	}

	@Override
	public boolean CanItemBeModified(ItemStack stack) {
		// TODO Auto-generated method stub
		return true;
	}
}
