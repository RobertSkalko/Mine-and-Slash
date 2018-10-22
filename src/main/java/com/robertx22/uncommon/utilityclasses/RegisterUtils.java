package com.robertx22.uncommon.utilityclasses;

import java.util.ArrayList;
import java.util.List;

import com.robertx22.mmorpg.Main;
import com.robertx22.mmorpg.Ref;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;

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
			registerRender(item);
		}
	}

	static int id = 0;

	public static void RegisterModEntity(Item item, Class<? extends Entity> theclass) {

		EntityRegistry.registerModEntity(new ResourceLocation(Ref.MODID + ":" + theclass.getName()), theclass,
				Ref.MODID + ":" + theclass.getName(), ++id, Main.instance, 64, 10, true);

		RenderingRegistry.registerEntityRenderingHandler(theclass,
				renderManager -> new RenderSnowball<>(renderManager, item, Minecraft.getMinecraft().getRenderItem()));

	}

	public static void registerRender(Item item) {
		ModelLoader.setCustomModelResourceLocation(item, 0,
				new ModelResourceLocation(item.getRegistryName(), "inventory"));

		ModelResourceLocation modelResourceLocation = new ModelResourceLocation(item.getRegistryName(), "inventory");

		ModelLoader.setCustomMeshDefinition(item, stack -> modelResourceLocation);

	}

}
