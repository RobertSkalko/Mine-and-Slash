package com.robertx22.onevent;

import com.robertx22.capability.EntityData;
import com.robertx22.generation.GearGen;
import com.robertx22.generation.GearGenSchema;
import com.robertx22.saveclasses.Unit;
import com.robertx22.saving.Saving;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;

public class OnLogin {

	@SubscribeEvent
	public void onLogin(PlayerLoggedInEvent event) {

		if (event.player.world.isRemote) {
			return;
		}

		EntityPlayer player = event.player;

		if (!player.hasCapability(EntityData.Data, null)) {

			return;
		}

		EntityData.IData data = player.getCapability(EntityData.Data, null);

		if (Saving.Load(data.getNBT(), Unit.class) == null) {
			data.setNBT(Saving.Save(null, new Unit(player)));
			System.out.println("Welcome!");
		}

		for (int i = 0; i < 20; i++) {
			player.addItemStackToInventory(GearGen.Create(new GearGenSchema(1)));
		}

	}

}
