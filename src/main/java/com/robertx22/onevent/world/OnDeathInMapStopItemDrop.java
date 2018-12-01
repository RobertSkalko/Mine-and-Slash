package com.robertx22.onevent.world;

import com.robertx22.uncommon.capability.PlayerDeathItems;
import com.robertx22.uncommon.capability.WorldData.IWorldData;
import com.robertx22.uncommon.datasaving.Load;

import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class OnDeathInMapStopItemDrop {

    // if dies in map, delete items and store them in capability
    @SubscribeEvent
    public static void onPlayerDeathDontDropItems(PlayerDropsEvent event) {

	if (event.getEntityPlayer().world.isRemote == false) {

	    try {
		IWorldData data = Load.World(event.getEntityPlayer().world);
		if (data != null && data.isMapWorld()) {
		    event.getEntityPlayer().getCapability(PlayerDeathItems.Data, null).saveItems(event.getDrops());
		}
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
