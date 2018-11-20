package com.robertx22.uncommon.commands;

import com.robertx22.dimensions.BaseWorldProvider;
import com.robertx22.dimensions.MyTeleporter;
import com.robertx22.mmorpg.Ref;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;

public class PortDimension extends CommandBase {

	@Override
	public String getName() {
		return "port";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "/port number_id";
	}

	public static DimensionType testDimensionType;

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {

		int id = Integer.valueOf(args[0]);
		EntityPlayer player = (EntityPlayer) sender;
		if (!DimensionManager.isDimensionRegistered(id)) {

			testDimensionType = DimensionType.register(Ref.MODID, "_test", id, BaseWorldProvider.class, false);
			DimensionManager.registerDimension(id, testDimensionType);
			DimensionManager.initDimension(id);

		}

		player.changeDimension(id, new MyTeleporter(player.getPosition()));

	}

}