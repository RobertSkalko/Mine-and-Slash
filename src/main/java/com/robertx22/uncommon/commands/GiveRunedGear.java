package com.robertx22.uncommon.commands;

import com.robertx22.generation.RunedGearGen;
import com.robertx22.generation.blueprints.RunedGearBlueprint;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;

public class GiveRunedGear extends CommandBase {

    @Override
    public String getName() {
	return "giverunedgear";
    }

    @Override
    public String getUsage(ICommandSender sender) {
	return "/giverunedgear (lvl), (rarity 0-5), (type: Sword, Necklace etc), (amount)  NOTE: It's Caps sensitive! Sword, not sword.";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {

	int lvl = Integer.valueOf(args[0]);
	int rarity = Integer.valueOf(args[1]);
	String type = args[2];
	int amount = Integer.valueOf(args[3]);

	RunedGearBlueprint blueprint = new RunedGearBlueprint(lvl);
	if (rarity > -1) {
	    blueprint.SetSpecificRarity(rarity);
	}
	if (!type.equals("random")) {
	    blueprint.SetSpecificType(type);
	}
	blueprint.LevelRange = false;

	EntityPlayer player = (EntityPlayer) sender;

	for (int i = 0; i < amount; i++) {
	    player.addItemStackToInventory(RunedGearGen.CreateStack(blueprint));
	}

    }
}