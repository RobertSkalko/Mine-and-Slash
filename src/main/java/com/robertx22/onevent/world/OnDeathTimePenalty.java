package com.robertx22.onevent.world;

import com.robertx22.uncommon.capability.WorldData.IWorldData;
import com.robertx22.uncommon.datasaving.Load;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class OnDeathTimePenalty {

	@SubscribeEvent
	public static void onPlayerDeathDontDropItems(LivingDeathEvent event) {

		try {
			if (event.getEntityLiving().world.isRemote == false && event.getEntityLiving() instanceof EntityPlayer) {

				EntityPlayer player = (EntityPlayer) event.getEntityLiving();
				World world = player.world;
				IWorldData data = Load.World(world);

				if (data != null && data.isMapWorld()) {

					data.onPlayerDeath(player, world);

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
