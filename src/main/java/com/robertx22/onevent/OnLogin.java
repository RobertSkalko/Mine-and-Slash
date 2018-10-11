package com.robertx22.onevent;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;

public class OnLogin {

	@SubscribeEvent
	public void onLogin(PlayerLoggedInEvent event) {

		if (event.player.world.isRemote) {
			return;
		}
		/*
		 * 
		 * EntityPlayer player = event.player;
		 * 
		 * PlayerData.giveExp(player, 0);
		 * 
		 * if (player.hasCapability(EntityData.Data, null) &&
		 * !player.getCapability(EntityData.Data,
		 * null).getNBT().getBoolean(Tags.ENTITY_INFO)) {
		 * 
		 * EntityData.IData data = player.getCapability(EntityData.Data, null);
		 * 
		 * NBTTagCompound nbt = GeneralUtils.getdefaultEntityNBT();
		 * nbt.setBoolean(Tags.ENTITY_INFO, true);
		 * 
		 * player.addItemStackToInventory(GearCreator.createGear(1, 0, Tags.WEAPON));
		 * player.addItemStackToInventory(new ItemStack(Blocks.ANVIL));
		 * 
		 * data.setNBT(nbt);
		 * 
		 * 
		 * }
		 */
	}

}
