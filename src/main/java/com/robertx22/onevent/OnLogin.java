package com.robertx22.onevent;

import com.robertx22.capability.EntityData;
import com.robertx22.saveclasses.Unit;
import com.robertx22.saving.Saving;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;

@Mod.EventBusSubscriber
public class OnLogin {

	@SubscribeEvent
	public static void onLogin(PlayerLoggedInEvent event) {

		if (event.player.world.isRemote) {
			return;
		}

		EntityPlayer player = event.player;

		/*
		 * for (int i = 0; i < 3; i++) {
		 * player.addItemStackToInventory(GearGen.Create(new GearGenSchema(1))); }
		 * 
		 **/
		if (!player.hasCapability(EntityData.Data, null)) {

			return;
		}

		if (Saving.Load(player, Unit.class) == null) {
			Saving.Save(player, new Unit(player));
			System.out.println("Welcome!");
		}

	}

}
