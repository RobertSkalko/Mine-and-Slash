package com.robertx22.commands;

import com.robertx22.generation.SpellItemGen;
import com.robertx22.generation.blueprints.SpellBlueprint;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;

public class GiveSpell extends CommandBase {

    @Override
    public String getName() {
	return "givespell";
    }

    @Override
    public String getUsage(ICommandSender sender) {
	return "/givespell (player) (lvl) (rarity 0-4) (type: frostbolt, firebolt etc) (amount)";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {

    	if (args.length < 5) throw new WrongUsageException("/givespell (player) (lvl) (rarity 0-4) (type: frostbolt, firebolt etc) (amount)");
    	int lvl = Integer.valueOf(args[1]);
    	int rarity = Integer.valueOf(args[2]);
    	String type = args[3];
    	int amount = Integer.valueOf(args[4]);

    	SpellBlueprint blueprint = new SpellBlueprint(lvl);
    	if (rarity > -1) {
    		blueprint.SetSpecificRarity(rarity);
    	}
    	if (!type.equals("random")) {
    		blueprint.SetSpecificType(type);
    	}
    	blueprint.LevelRange = false;

    	EntityPlayer player = getPlayer(server, sender, args[0]);

    	for (int i = 0; i < amount; i++) {
    		player.addItemStackToInventory(SpellItemGen.Create(blueprint));
    	}
    }
}