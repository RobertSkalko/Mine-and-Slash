package com.robertx22.commands;

import com.robertx22.uncommon.capability.EntityData;
import com.robertx22.uncommon.datasaving.Load;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;

public class GiveExp extends CommandBase {

	@Override
	public String getName() {
		return "givexp";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "/givexp (player) (exp)";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {

		if (args.length < 2)
			throw new WrongUsageException("/givexp (player) (exp)");

		int exp = parseInt(args[1], 0);
		EntityPlayer player = getPlayer(server, sender, args[0]);

		try {
			Load.Unit(player).GiveExp(player, exp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}