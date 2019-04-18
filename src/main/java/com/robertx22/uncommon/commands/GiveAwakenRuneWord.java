package com.robertx22.uncommon.commands;

import com.robertx22.generation.AwakenRuneWordGen;
import com.robertx22.generation.blueprints.AwakenRuneWordBlueprint;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;

public class GiveAwakenRuneWord extends CommandBase {

    @Override
    public String getName() {
	return "giveawakenruneword";
    }

    @Override
    public String getUsage(ICommandSender sender) {
	return "/giveawakenruneword (runeword name or random), (amount)";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {

	String word = args[0];
	int amount = Integer.valueOf(args[1]);

	for (int i = 0; i < amount; i++) {
	    AwakenRuneWordBlueprint blueprint = new AwakenRuneWordBlueprint();
	    if (word != "random") {
		blueprint.word = word;
	    }

	    EntityPlayer player = (EntityPlayer) sender;

	    try {
		player.addItemStackToInventory(AwakenRuneWordGen.Create(blueprint));
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}

    }
}