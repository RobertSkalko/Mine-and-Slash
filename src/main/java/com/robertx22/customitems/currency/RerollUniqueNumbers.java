package com.robertx22.customitems.currency;

import java.util.List;

import javax.annotation.Nullable;

import com.robertx22.mmorpg.Ref;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.uncommon.datasaving.Gear;
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
public class RerollUniqueNumbers extends CurrencyItem implements ICurrencyItemEffect {

    private static final String name = "reroll_unique_numbers";

    @GameRegistry.ObjectHolder(Ref.MODID + ":reroll_unique_numbers")
    public static final Item ITEM = null;

    public RerollUniqueNumbers() {

	super(name);

    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
	event.getRegistry().register(new RerollUniqueNumbers());
    }

    @SubscribeEvent
    public static void onModelRegistry(ModelRegistryEvent event) {
	RegisterUtils.registerRender(ITEM);
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {

	tooltip.add("Use on an item to re-roll unique stat numbers");

	this.TooltipQuote(tooltip, "Anything can change.");

    }

    @Override
    public ItemStack ModifyItem(ItemStack stack) {

	GearItemData gear = Gear.Load(stack);

	gear.uniqueStats.RerollNumbers(gear);

	Gear.Save(stack, gear);

	return stack;
    }

    @Override
    public boolean CanItemBeModified(ItemStack stack) {
	GearItemData gear = Gear.Load(stack);

	return gear != null && gear.uniqueStats != null && gear.isUnique;
    }

    @Override
    public int Tier() {
	return 14;
    }

    @Override
    public int Weight() {
	return this.EpicWeight;
    }
}