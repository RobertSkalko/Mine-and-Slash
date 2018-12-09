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
public class ItemAddSecondaryStat extends CurrencyItem implements ICurrencyItemEffect {
    @Override
    public String GUID() {
	return "add_secondary_stat";
    }

    private static final String name = "add_secondary_stat";

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

    @Override
    public ItemStack ModifyItem(ItemStack stack) {

	GearItemData gear = Gear.Load(stack);

	gear.secondaryStats.AddStat(gear);
	Gear.Save(stack, gear);

	return stack;
    }

    @Override
    public boolean CanItemBeModified(ItemStack stack) {
	GearItemData gear = Gear.Load(stack);

	if (gear.secondaryStats != null && gear.secondaryStats.AddedStat == false) {
	    return true;
	}

	return false;
    }

    @Override
    public int Tier() {
	return 0;
    }
}