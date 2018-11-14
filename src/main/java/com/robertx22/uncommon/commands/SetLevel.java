package com.robertx22.uncommon.commands;

import com.robertx22.saveclasses.Unit;
import com.robertx22.uncommon.datasaving.UnitSaving;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;

public class SetLevel extends CommandBase {

	@Override
	public String getName() {
		return "setlevel";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "/setlevel (lvl)";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {

		int lvl = Integer.valueOf(args[0]);

		EntityPlayer player = (EntityPlayer) sender;

		Unit unit = UnitSaving.Load(player);

		unit.SetLevel(lvl);

		UnitSaving.Save(player, unit);

	}
}