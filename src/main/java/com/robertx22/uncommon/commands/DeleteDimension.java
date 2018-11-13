package com.robertx22.uncommon.commands;

import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.robertx22.dimensions.MyTeleporter;
import com.robertx22.dimensions.WorldFileUtils;

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

		int id = Integer.valueOf(args[0]);
		EntityPlayer player = (EntityPlayer) sender;
		if (DimensionManager.isDimensionRegistered(id)) {

			// DimensionManager.unloadWorld(id);

			World world = DimensionManager.getWorld(id);

			world.loadedEntityList.clear();

			world = null;

			try {
				FileUtils.forceDelete(WorldFileUtils.getWorldDirectory(DimensionManager.getWorld(id)));

				DimensionManager.unregisterDimension(id);
			} catch (IOException e) {

				e.printStackTrace();
			}

		}

		player.changeDimension(id, new MyTeleporter(player.getPosition()));

	}
}