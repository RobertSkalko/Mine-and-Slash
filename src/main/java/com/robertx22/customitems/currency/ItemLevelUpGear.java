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
public class ItemLevelUpGear extends CurrencyItem implements ICurrencyItemEffect {

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

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {

	tooltip.add("Extremely rare crystal,");
	tooltip.add("Used to increase the level of an item.");
	tooltip.add("A single item can't be leveled up more than " + MAXIMUM_LEVEL_UPS + " times!");

	this.TooltipQuote(tooltip, "Who said your sword can't level with you?");

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
    public int Weight() {
	return this.RareWeight;
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