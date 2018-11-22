package com.robertx22.onevent.world;

import java.util.List;

import org.apache.commons.io.FileUtils;

import com.robertx22.dimensions.WorldFileUtils;
import com.robertx22.uncommon.capability.WorldData;
import com.robertx22.uncommon.capability.WorldData.IWorldData;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class CheckWorldDelete {

	@SubscribeEvent
	public static void onWorldUnload(WorldEvent.Unload event) {

		if (false) {
			deleteIfSet(event.getWorld());
		}
	}

	private static void deleteIfSet(World world) {

		if (world.hasCapability(WorldData.Data, null)) {
			IWorldData data = world.getCapability(WorldData.Data, null);

			if (data != null && data.isSetForDelete()) {

				transferPlayersBack(world.playerEntities);

				if (FileUtils.deleteQuietly(WorldFileUtils.getWorldDirectory(world))) {
					DimensionManager.unregisterDimension(world.provider.getDimension());
					System.out.println("Deleting a temporary map world to free up disk space!");
				}
			}

		}
	}

	private static void transferPlayersBack(List<EntityPlayer> players) {

		if (players != null) {
			for (EntityPlayer player : players) {
				player.changeDimension(0);
			}
		}

	}
}
