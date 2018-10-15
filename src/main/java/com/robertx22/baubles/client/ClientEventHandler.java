package com.robertx22.baubles.client;

import com.robertx22.baubles.common.network.PacketHandler;
import com.robertx22.baubles.common.network.PacketOpenBaublesInventory;

import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.relauncher.Side;

public class ClientEventHandler
{
	@SubscribeEvent
	public void registerItemModels(ModelRegistryEvent event) {
		//ModelLoader.setCustomModelResourceLocation(ItemRing.RING, 0, new ModelResourceLocation("baubles:ring", "inventory"));
	}

	@SubscribeEvent
	public void playerTick(PlayerTickEvent event) {
		if (event.side == Side.CLIENT && event.phase == Phase.START ) {
			if (ClientProxy.KEY_BAUBLES.isPressed() && FMLClientHandler.instance().getClient().inGameHasFocus) {
					PacketHandler.INSTANCE.sendToServer(new PacketOpenBaublesInventory());
			}
		}
	}


}
