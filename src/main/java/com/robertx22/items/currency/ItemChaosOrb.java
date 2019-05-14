package com.robertx22.items.currency;

import com.robertx22.mmorpg.Ref;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.saveclasses.gearitem.ChaosStatsData;
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
public class ItemChaosOrb extends CurrencyItem implements ICurrencyItemEffect {
    @Override
    public String GUID() {
	return "chaos_orb";
    }

    private static final String name = "chaos_orb";

    @GameRegistry.ObjectHolder(Ref.MODID + ":" + name)
    public static final Item ITEM = null;

    public ItemChaosOrb() {

	super(name);

    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
	event.getRegistry().register(new ItemChaosOrb());
    }

    @SubscribeEvent
    public static void onModelRegistry(ModelRegistryEvent event) {
	RegisterUtils.registerRender(ITEM);
    }

    @Override
    public ItemStack ModifyItem(ItemStack stack, ItemStack Currency) {

	GearItemData gear = Gear.Load(stack);
	gear.chaosStats = new ChaosStatsData();
	gear.chaosStats.RerollFully(gear);
	Gear.Save(stack, gear);

	return stack;
    }

    @Override
    public boolean canItemBeModified(ItemStack stack, ItemStack Currency) {

	GearItemData gear = Gear.Load(stack);

	if (gear.chaosStats == null && !gear.isRuned()) {
	    return true;
	}

	return false;
    }

    @Override
    public int Tier() {
	return 3;
    }

    @Override
    public int Rank() {
	return 1;
    }
}
