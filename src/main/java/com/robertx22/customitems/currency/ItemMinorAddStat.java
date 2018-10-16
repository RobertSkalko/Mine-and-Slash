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
public class ItemMinorAddStat extends ItemBasic implements ICurrencyItemEffect {

	private static final String name = Ref.MODID + ":item_minor_add_stat";

	@GameRegistry.ObjectHolder(Ref.MODID + ":item_minor_add_stat")
	public static final Item ITEM = null;

	public ItemMinorAddStat() {

		super(name);

	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		event.getRegistry().register(new ItemMinorAddStat());
	}

	@SubscribeEvent
	public static void onModelRegistry(ModelRegistryEvent event) {
		ModelUtils.registerRender(ITEM);
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {

		stack.setStackDisplayName("Adamant Of The Future");

		tooltip.add("This material can be used to add an enhancement to a weak item.");

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