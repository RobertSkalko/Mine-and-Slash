package com.robertx22.customitems.currency;

import com.robertx22.mmorpg.Ref;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.saveclasses.gearitem.PrefixData;
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
public class ItemAddPrefix extends CurrencyItem implements ICurrencyItemEffect {

    @Override
    public String GUID() {
	return "add_prefix";
    }

    private static final String name = "add_prefix";

    @GameRegistry.ObjectHolder(Ref.MODID + ":add_prefix")
    public static final Item ITEM = null;

    public ItemAddPrefix() {

	super(name);

    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
	event.getRegistry().register(new ItemAddPrefix());
    }

    @SubscribeEvent
    public static void onModelRegistry(ModelRegistryEvent event) {
	RegisterUtils.registerRender(ITEM);
    }

    @Override
    public ItemStack ModifyItem(ItemStack stack, ItemStack Currency) {

	GearItemData gear = Gear.Load(stack);

	gear.prefix = new PrefixData();
	gear.prefix.RerollFully(gear);

	Gear.Save(stack, gear);

	return stack;
    }

    @Override
    public boolean canItemBeModified(ItemStack stack, ItemStack Currency) {
	GearItemData gear = Gear.Load(stack);

	return gear != null && gear.prefix == null && !gear.isRuned();
    }

    @Override
    public int Tier() {
	return 10;
    }

    @Override
    public int Rank() {
	return 4;
    }
}