package com.robertx22.commands;

import com.robertx22.generation.RunedGearGen;
import com.robertx22.generation.blueprints.RunedGearBlueprint;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;

public class GiveRunedGear extends CommandBase {

    @Override
    public String getName() {
	return "giverunedgear";
    }

    @Override
    public String getUsage(ICommandSender sender) {
	return "/giverunedgear (player) (lvl) (rarity 0-5) (type: Sword, Necklace etc) (amount)  NOTE: It's Caps sensitive! Sword, not sword.";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {

    	if (args.length < 5) throw new WrongUsageException("/giverunedgear (player) (lvl) (rarity 0-5) (type: Sword, Necklace etc) (amount)  NOTE: It's Caps sensitive! Sword, not sword.");
    	int lvl = Integer.valueOf(args[1]);
    	int rarity = Integer.valueOf(args[2]);
    	String type = args[3];
    	int amount = Integer.valueOf(args[4]);

    	RunedGearBlueprint blueprint = new RunedGearBlueprint(lvl);
    	if (rarity > -1) {
    		blueprint.SetSpecificRarity(rarity);
    	}
    	if (!type.equals("random")) {
    		blueprint.SetSpecificType(type);
    	}
    	blueprint.LevelRange = false;

    	EntityPlayer player = getPlayer(server, sender, args[0]);

    	for (int i = 0; i < amount; i++) {
    		player.addItemStackToInventory(RunedGearGen.CreateStack(blueprint));
    	}

    }
}