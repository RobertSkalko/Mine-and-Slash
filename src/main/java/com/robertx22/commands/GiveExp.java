package com.robertx22.commands;

import com.robertx22.uncommon.capability.EntityData;
import com.robertx22.uncommon.datasaving.Load;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;

public class GiveExp extends CommandBase {

    @Override
    public String getName() {
	return "givexp";
    }

    @Override
    public String getUsage(ICommandSender sender) {
	return "/givexp (exp)";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {

	int exp = Integer.valueOf(args[0]);

	EntityPlayer player = (EntityPlayer) sender;
	
	try {
        Load.Unit(player).GiveExp(player, exp);
    } catch (Exception e) {
        e.printStackTrace();
    }

    }
}
