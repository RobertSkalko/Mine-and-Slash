package com.robertx22.commands;

import com.robertx22.mmorpg.ModConfig;
import com.robertx22.player.PlayerData;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

public class SetLevel extends CommandBase {

	@Override
	public String getName() {
		return "setlevel";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "/setlevel (lvl) 1";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {

		if (ModConfig.Cheats.CHEAT_MODE) {

			int lvl = Integer.valueOf(args[0]);

			EntityPlayer player = (EntityPlayer) sender;

			PlayerData.setLevel(player, lvl);

		} else {
			sender.sendMessage(new TextComponentString("You have to enable Cheats in mod config to use commands!"));
		}

	}
}