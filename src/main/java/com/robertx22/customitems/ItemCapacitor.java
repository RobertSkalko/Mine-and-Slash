package com.robertx22.customitems;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.robertx22.customitems.currency.CurrencyItem;
import com.robertx22.database.lists.Rarities;
import com.robertx22.mmorpg.Ref;
import com.robertx22.uncommon.utilityclasses.RegisterUtils;

import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class ItemCapacitor extends Item {

	public static HashMap<Integer, Item> Items = new HashMap<Integer, Item>();

	public ItemCapacitor(int rarity) {
		this.rarity = rarity;

		this.setMaxDamage(0);
		this.setCreativeTab(CurrencyItem.CurrencyTab);
		this.setUnlocalizedName("capacitor" + rarity);
		this.setRegistryName(Ref.MODID + ":capacitor" + rarity);
	}

	int rarity;

	public List<Float> RepairValues = Arrays.asList(0.95F, 0.9F, 0.8F, 0.7F, 0.5F, 0.25F);

	public Float GetFuelMultiplier() {

		return RepairValues.get(rarity);

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
