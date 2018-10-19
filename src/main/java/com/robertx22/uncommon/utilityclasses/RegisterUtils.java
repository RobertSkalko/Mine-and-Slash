package com.robertx22.uncommon.utilityclasses;

import java.util.ArrayList;
import java.util.List;


import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class RegisterUtils {

	private static List<Item> ItemsToRegister = new ArrayList<Item>();
	
	public static void Add(Item item) {
		ItemsToRegister.add(item);
		
	}
	
	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		for (Item item : ItemsToRegister) {
		event.getRegistry().register(item);
		}
		}

	@SubscribeEvent
	public static void onModelRegistry(ModelRegistryEvent event) {
		for (Item item : ItemsToRegister) {
			ModelUtils.registerRender(item);
			}
			}
	
	
}
