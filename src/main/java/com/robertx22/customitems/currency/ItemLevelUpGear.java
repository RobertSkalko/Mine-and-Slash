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
public class ItemLevelUpGear extends CurrencyItem implements ICurrencyItemEffect {
    @Override
    public String GUID() {
	return "item_levelup";
    }

    private static final String name = "item_levelup";

    @GameRegistry.ObjectHolder(Ref.MODID + ":item_levelup")
    public static final Item ITEM = null;

    public ItemLevelUpGear() {

	super(name);

    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
	event.getRegistry().register(new ItemLevelUpGear());
    }

    @SubscribeEvent
    public static void onModelRegistry(ModelRegistryEvent event) {
	RegisterUtils.registerRender(ITEM);

    }

    @Override
    public ItemStack ModifyItem(ItemStack stack) {
	GearItemData gear = Gear.Load(stack);
	gear.level++;
	gear.timesLeveledUp++;
	Gear.Save(stack, gear);

	return stack;
    }

    public static final int MAXIMUM_LEVEL_UPS = 10;

    @Override
    public int Rank() {
	return 2;
    }

    @Override
    public boolean CanItemBeModified(ItemStack stack) {
	GearItemData gear = Gear.Load(stack);

	return gear != null && gear.timesLeveledUp < MAXIMUM_LEVEL_UPS;
    }

    @Override
    public int Tier() {
	return 5;
    }

}