package com.robertx22.customitems.armor;

import java.util.HashMap;

import com.robertx22.customitems.bases.BaseArmorItem;
import com.robertx22.database.lists.Rarities;
import com.robertx22.utilityclasses.ModelUtils;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class ItemChest extends BaseArmorItem {
	public static HashMap<Integer, Item> Items = new HashMap<Integer, Item>();

	public ItemChest(int rarity, HashMap<Integer, Item> map, EntityEquipmentSlot slot) {
		super(rarity, map, slot);

	}

	@Override
	public String Name() {
		return "Chest";
	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		Rarities.Items.forEach((x) -> Items.put(x.Rank(), new ItemChest(x.Rank(), Items, EntityEquipmentSlot.CHEST)));
		Items.values().forEach((x) -> event.getRegistry().register(x));
	}

	@SubscribeEvent
	public static void onModelRegistry(ModelRegistryEvent event) {
		Items.values().forEach((x) -> ModelUtils.registerRender(x));
	}

}
