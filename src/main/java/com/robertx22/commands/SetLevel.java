package com.robertx22.commands;

import com.robertx22.uncommon.capability.EntityData;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;

public class SetLevel extends CommandBase {

	@Override
	public String getName() {
		return "setlevel";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "/setlevel (player) (lvl)";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {

		if (args.length < 2)
			throw new WrongUsageException("/setlevel (player) (lvl)");

		int lvl = parseInt(args[1], 0);

		EntityPlayer player = getPlayer(server, sender, args[0]);

		player.getCapability(EntityData.Data, null).setLevel(lvl, player);

	}
}