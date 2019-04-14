package com.robertx22.customitems.misc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Nullable;

import com.robertx22.db_lists.CreativeTabList;
import com.robertx22.db_lists.Rarities;
import com.robertx22.uncommon.CLOC;
import com.robertx22.uncommon.utilityclasses.RegisterItemUtils;
import com.robertx22.uncommon.utilityclasses.RegisterUtils;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@EventBusSubscriber
public class ItemCapacitor extends Item {

    public static HashMap<Integer, Item> Items = new HashMap<Integer, Item>();

    public ItemCapacitor(int rarity) {
	this.rarity = rarity;

	this.setMaxDamage(0);
	this.setCreativeTab(CreativeTabList.CurrencyTab);

	RegisterItemUtils.RegisterItemName(this, "capacitor" + rarity);
    }

    int rarity;

    public List<Float> RepairValues = Arrays.asList(0.95F, 0.9F, 0.8F, 0.7F, 0.6F, 0.5F);

    public Float GetFuelMultiplier() {

	return RepairValues.get(rarity);

    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {

	tooltip.add(CLOC.tooltip("capacitor"));

	tooltip.add(CLOC.tooltip("capacitor2") + ": " + this.GetFuelMultiplier() + "x");

    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
	Rarities.Items.forEach((x) -> Items.put(x.Rank(), new ItemCapacitor(x.Rank())));
	Items.values().forEach((x) -> event.getRegistry().register(x));
    }

    @SubscribeEvent
    public static void onModelRegistry(ModelRegistryEvent event) {
	Items.values().forEach((x) -> RegisterUtils.registerRender(x));
    }

}
