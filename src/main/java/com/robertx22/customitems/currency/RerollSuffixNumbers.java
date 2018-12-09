package com.robertx22.customitems.currency;

import com.robertx22.mmorpg.Ref;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.uncommon.datasaving.Gear;
import com.robertx22.uncommon.utilityclasses.RegisterUtils;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@EventBusSubscriber
public class RerollSuffixNumbers extends CurrencyItem implements ICurrencyItemEffect {
    @Override
    public String GUID() {
	return "reroll_suffix_numbers";
    }

    private static final String name = "reroll_suffix_numbers";

    @GameRegistry.ObjectHolder(Ref.MODID + ":reroll_suffix_numbers")
    public static final Item ITEM = null;

    public RerollSuffixNumbers() {

	super(name);

    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
	event.getRegistry().register(new RerollSuffixNumbers());
    }

    @SubscribeEvent
    public static void onModelRegistry(ModelRegistryEvent event) {
	RegisterUtils.registerRender(ITEM);
    }

    @Override
    public ItemStack ModifyItem(ItemStack stack) {

	GearItemData gear = Gear.Load(stack);

	gear.suffix.RerollNumbers(gear);

	Gear.Save(stack, gear);

	return stack;
    }

    @Override
    public boolean CanItemBeModified(ItemStack stack) {
	GearItemData gear = Gear.Load(stack);

	return gear != null && gear.suffix != null;
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