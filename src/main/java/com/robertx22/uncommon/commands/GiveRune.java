package com.robertx22.uncommon.commands;

import com.robertx22.generation.RuneGen;
import com.robertx22.generation.blueprints.RuneBlueprint;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;

public class GiveRune extends CommandBase {

    @Override
    public String getName() {
	return "giverune";
    }

    @Override
    public String getUsage(ICommandSender sender) {
	return "/giverune (lvl), (rarity 0-5), (amount)";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {

	int lvl = Integer.valueOf(args[0]);
	int rarity = Integer.valueOf(args[1]);
	int amount = Integer.valueOf(args[2]);

	for (int i = 0; i < amount; i++) {
	    RuneBlueprint blueprint = new RuneBlueprint(lvl);
	    if (rarity > -1) {
		blueprint.SetSpecificRarity(rarity);
	    }

	    blueprint.LevelRange = false;

	    EntityPlayer player = (EntityPlayer) sender;

	    player.addItemStackToInventory(RuneGen.Create(blueprint));
	}

    }
}