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
public class ItemRandomizeSuffix extends CurrencyItem implements ICurrencyItemEffect {
    @Override
    public String GUID() {
	return "randomize_suffix";
    }

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

    @Override
    public ItemStack ModifyItem(ItemStack stack) {
	GearItemData gear = Gear.Load(stack);
	gear.suffix.RerollFully(gear);
	Gear.Save(stack, gear);

	return stack;
    }

    @Override
    public boolean CanItemBeModified(ItemStack stack) {

	GearItemData gear = Gear.Load(stack);

	if (gear.suffix != null) {
	    return true;
	}

	return false;
    }

    @Override
    public int Tier() {
	return 0;
    }

    @Override
    public int Rank() {
	return 0;
    }
}
