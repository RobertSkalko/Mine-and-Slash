package com.robertx22.customitems.currency;

import java.util.List;

import javax.annotation.Nullable;

import com.robertx22.mmorpg.Ref;
import com.robertx22.saveclasses.gearitem.GearItemData;
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
public class ItemRandomizePrefix extends CurrencyItem implements ICurrencyItemEffect {

	private static final String name = Ref.MODID + ":randomize_prefix";

	@GameRegistry.ObjectHolder(Ref.MODID + ":randomize_prefix")
	public static final Item ITEM = null;

	public ItemRandomizePrefix() {

		super(name);

	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		event.getRegistry().register(new ItemRandomizePrefix());
	}

	@SubscribeEvent
	public static void onModelRegistry(ModelRegistryEvent event) {
		RegisterUtils.registerRender(ITEM);
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {

		stack.setStackDisplayName("Orb Of Ever-Changing Prefix");

		tooltip.add("Fully randomizes the Prefix of an Item.");

	}

	@Override
	public void ModifyItem(ItemStack stack) {
		GearItemData gear = GearSaving.Load(stack);
		gear.prefix.RerollFully(gear);
		gear.HideInfoForCrafting(stack);
	}

	@Override
	public boolean CanItemBeModified(ItemStack stack) {

		GearItemData gear = GearSaving.Load(stack);

		if (gear.prefix != null) {
			return true;
		}

		return false;
	}

}