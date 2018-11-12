package com.robertx22.customitems.currency;

import java.util.List;

import javax.annotation.Nullable;

import com.robertx22.mmorpg.Ref;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.uncommon.datasaving.GearSaving;
import com.robertx22.uncommon.utilityclasses.RegisterUtils;

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
public class ItemRandomizeSuffix extends CurrencyItem implements ICurrencyItemEffect {

	private static final String name = "randomize_suffix";

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
		RegisterUtils.registerRender(ITEM);
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {

		tooltip.add("Fully randomizes the Suffix of an Item.");

		this.TooltipQuote(tooltip, "There is always a better choice");

	}

	@Override
	public ItemStack ModifyItem(ItemStack stack) {
		GearItemData gear = GearSaving.Load(stack);
		gear.suffix.RerollFully(gear);
		GearSaving.Save(stack, gear);

		return stack;
	}

	@Override
	public boolean CanItemBeModified(ItemStack stack) {

		GearItemData gear = GearSaving.Load(stack);

		if (gear.suffix != null) {
			return true;
		}

		return false;
	}
}
