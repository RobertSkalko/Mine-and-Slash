
package com.robertx22.baubles.client;

import java.util.Map;
import org.lwjgl.input.Keyboard;

import com.robertx22.baubles.client.gui.GuiEvents;
import com.robertx22.baubles.client.gui.GuiPlayerExpanded;
import com.robertx22.baubles.common.CommonProxy;
import com.robertx22.mmorpg.Main;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {

	public static final KeyBinding KEY_BAUBLES = new KeyBinding("keybind.baublesinventory", Keyboard.KEY_B, "key.categories.inventory");

	@Override
	public void registerEventHandlers() {
		super.registerEventHandlers();

		ClientRegistry.registerKeyBinding(KEY_BAUBLES);

		MinecraftForge.EVENT_BUS.register(new ClientEventHandler());
		MinecraftForge.EVENT_BUS.register(new GuiEvents());
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (world instanceof WorldClient) {
			switch (ID) {
				case Main.GUI: return new GuiPlayerExpanded(player);
			}
		}
		return null;
	}

	@Override
	public World getClientWorld() {
		return FMLClientHandler.instance().getClient().world;
	}

	@Override
	public void init() {
		Map<String, RenderPlayer> skinMap = Minecraft.getMinecraft().getRenderManager().getSkinMap();
		RenderPlayer render;
		render = skinMap.get("default");
		render.addLayer(new BaublesRenderLayer());

		render = skinMap.get("slim");
		render.addLayer(new BaublesRenderLayer());
	}
}
