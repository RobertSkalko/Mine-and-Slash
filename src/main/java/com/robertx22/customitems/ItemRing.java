package com.robertx22.customitems;

import java.util.HashMap;

import com.robertx22.baubles.api.BaubleType;
import com.robertx22.database.lists.Rarities;
import com.robertx22.gearitem.ItemRarity;
import com.robertx22.utilityclasses.ModelUtils;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class ItemRing extends BaseBaublesItem {

	public static HashMap<Integer, Item> Rings = new HashMap<Integer, Item>();

	public ItemRing(int i) {
		super(i);

	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {

		for (ItemRarity rarity : Rarities.Items) {
			Rings.put(rarity.Rank(), new ItemRing(rarity.Rank()));
		}

		for (Item item : Rings.values()) {
			event.getRegistry().register(item);
		}
	}

	@SubscribeEvent
	public static void onModelRegistry(ModelRegistryEvent event) {
		for (Item item : Rings.values()) {
			ModelUtils.registerRender(item);
		}

	}

	@Override
	public BaubleType getBaubleType(ItemStack itemstack) {
		return BaubleType.RING;
	}

	@Override
	public String Name() {
		return "Ring";
	}
}
