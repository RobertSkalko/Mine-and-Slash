package com.robertx22.uncommon.utilityclasses;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

public class RegisterUtils {

	public static void registerRender(Item item) {
		ModelLoader.setCustomModelResourceLocation(item, 0,
				new ModelResourceLocation(item.getRegistryName(), "inventory"));

		ModelResourceLocation modelResourceLocation = new ModelResourceLocation(item.getRegistryName(), "inventory");

		ModelLoader.setCustomMeshDefinition(item, stack -> modelResourceLocation);

	}

}
