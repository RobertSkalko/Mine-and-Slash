package com.robertx22.uncommon.commands;

import com.robertx22.mmorpg.Main;
import com.robertx22.network.WorldPackage;
import com.robertx22.uncommon.capability.WorldData;
import com.robertx22.uncommon.capability.WorldData.IWorldData;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;

public class DeleteDimension extends CommandBase {

	@Override
	public String getName() {
		return "delete";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "/delete number_id";
	}

	public static DimensionType testDimensionType;

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {

		try {
			int id = Integer.valueOf(args[0]);
			EntityPlayer player = (EntityPlayer) sender;

			if (DimensionManager.isDimensionRegistered(id)) {

				World world = DimensionManager.getWorld(id);

				IWorldData data = world.getCapability(WorldData.Data, null);

				if (data != null && data.isMapWorld()) {
					data.delete(player, world);
					Main.Network.sendToAll(new WorldPackage(data.getNBT()));
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}