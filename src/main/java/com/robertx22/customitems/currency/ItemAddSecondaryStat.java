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
public class ItemAddSecondaryStat extends CurrencyItem implements ICurrencyItemEffect {

	private static final String name = Ref.MODID + ":add_secondary_stat";

	@GameRegistry.ObjectHolder(Ref.MODID + ":add_secondary_stat")
	public static final Item ITEM = null;

	public ItemAddSecondaryStat() {

		super(name);

	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		event.getRegistry().register(new ItemAddSecondaryStat());
	}

	@SubscribeEvent
	public static void onModelRegistry(ModelRegistryEvent event) {
		RegisterUtils.registerRender(ITEM);
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {

		stack.setStackDisplayName("Crystal Of Legend");

		tooltip.add("This material can be used to add an enhancement to an item.");

	}

	@Override
	public void ModifyItem(ItemStack stack) {

		GearItemData gear = GearSaving.Load(stack);
		gear.secondaryStats.AddStat = true;
		GearSaving.Save(stack, gear);

	}

	@Override
	public boolean CanItemBeModified(ItemStack stack) {
		GearItemData gear = GearSaving.Load(stack);

		if (gear.secondaryStats.AddedStat == false) {
			return true;
		}

		return false;
	}
}