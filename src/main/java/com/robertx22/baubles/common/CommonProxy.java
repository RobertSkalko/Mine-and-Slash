
package com.robertx22.baubles.common;

import com.robertx22.baubles.common.container.ContainerPlayerExpanded;
import com.robertx22.baubles.common.event.EventHandlerEntity;
import com.robertx22.baubles.common.event.EventHandlerItem;
import com.robertx22.mmorpg.Main;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class CommonProxy implements IGuiHandler {

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return null;
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (ID) {
		case Main.GUI:
			return new ContainerPlayerExpanded(player.inventory, !world.isRemote, player);
		}
		return null;
	}

	public World getClientWorld() {
		return null;
	}

	public void registerEventHandlers() {
		MinecraftForge.EVENT_BUS.register(new EventHandlerEntity());
		MinecraftForge.EVENT_BUS.register(new EventHandlerItem());
	}

	public void registerRenderers() {
	}

	public void init() {
	}
}
