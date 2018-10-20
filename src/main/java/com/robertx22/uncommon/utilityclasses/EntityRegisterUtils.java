package com.robertx22.uncommon.utilityclasses;

import com.robertx22.mmorpg.Main;
import com.robertx22.mmorpg.Ref;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class EntityRegisterUtils {

	static int id = 0;

	public static void Register(Item item, Class<? extends Entity> theclass) {

		EntityRegistry.registerModEntity(new ResourceLocation(Ref.MODID + theclass.toString()), theclass,
				theclass.toString(), ++id, Main.instance, 64, 10, true);

		RenderingRegistry.registerEntityRenderingHandler(theclass,
				renderManager -> new RenderSnowball<>(renderManager, item, Minecraft.getMinecraft().getRenderItem()));

	}
}
