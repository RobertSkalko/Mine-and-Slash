package com.robertx22.uncommon.commands;

import com.robertx22.dimensions.MyTeleporter;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
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

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {

	EntityPlayer player = (EntityPlayer) sender;
	int id = Integer.valueOf(args[0]);

	if (DimensionManager.isDimensionRegistered(id)) {
	    player.changeDimension(id,
		    new MyTeleporter(player.world, DimensionManager.getWorld(id).getSpawnPoint(), player, id));
	} else {
	    player.sendMessage(new TextComponentString("No such dimension"));
	}

    }

}