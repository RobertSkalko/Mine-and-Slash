package com.robertx22.onevent.world;

import com.robertx22.uncommon.capability.PlayerDeathItems;
import com.robertx22.uncommon.capability.PlayerDeathItems.IPlayerDrops;
import com.robertx22.uncommon.capability.WorldData.IWorldData;
import com.robertx22.uncommon.datasaving.Load;

import baubles.api.BaublesApi;
import baubles.api.cap.IBaublesItemHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class OnDeathInMapStopItemDrop {

    // this doesn't look that safe..
    // if dies in map, delete items and store them in capability
    @SubscribeEvent
    public static void onPlayerDeathDontDropItems(PlayerDropsEvent event) {

	if (event.getEntityPlayer().world.isRemote == false) {

	    try {

		EntityPlayer player = event.getEntityPlayer();
		World world = event.getEntityPlayer().world;
		IWorldData data = Load.World(world);

		IPlayerDrops capa = event.getEntityPlayer().getCapability(PlayerDeathItems.Data, null);

		if (data != null && data.isMapWorld()) {

		    if (!player.world.getGameRules().getBoolean("keepInventory") && !player.isSpectator()) {

			capa.saveItems(event.getDrops());

			IBaublesItemHandler baubles = BaublesApi.getBaublesHandler(player);
			for (int i = 0; i < baubles.getSlots(); i++) {

			    ItemStack stack = baubles.getStackInSlot(i);

			    if (stack != null && !stack.isEmpty()) {
				capa.saveItem(stack.copy());

				stack.setCount(0);
			    }
			}
		    }
		    data.onPlayerDeath(event.getEntityPlayer(), world); // if this goes first, you need to check if
									// players
		} // them. Otherwise all items are lost

	    } catch (Exception e) {
		e.printStackTrace();
	    }

	}
    }

    // on clone if items in capability, get them and delete (so no duplication)
    @SubscribeEvent
    public static void onPlayerCloneGiveBackItems(PlayerEvent.Clone event) {

	try {
	    // get items also deletes them from capability
	    // but now that i think about it, i dont save the capability anyway after death,
	    // so that's useless
	    for (ItemStack stack : event.getOriginal().getCapability(PlayerDeathItems.Data, null).getItems()) {
		if (event.getEntityPlayer().addItemStackToInventory(stack) == false) {
		    event.getEntityPlayer().entityDropItem(stack, 1F);
		}
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}

    }
}
