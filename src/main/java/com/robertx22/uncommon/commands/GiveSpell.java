package com.robertx22.uncommon.commands;

import com.robertx22.generation.SpellItemGen;
import com.robertx22.generation.blueprints.SpellBlueprint;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;

public class GiveSpell extends CommandBase {

    @Override
    public String getName() {
	return "givespell";
    }

    @Override
    public String getUsage(ICommandSender sender) {
	return "/givespell (lvl), (rarity 0-4), (type: frostbolt, firebolt etc), (amount)  NOTE: It's Caps sensitive! Sword, not sword.";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {

	int lvl = Integer.valueOf(args[0]);
	int rarity = Integer.valueOf(args[1]);
	String type = args[2];
	int amount = Integer.valueOf(args[3]);

	SpellBlueprint blueprint = new SpellBlueprint(lvl);
	if (rarity > -1) {
	    blueprint.SetSpecificRarity(rarity);
	}
	if (!type.equals("random")) {
	    blueprint.SetSpecificType(type);
	}
	blueprint.LevelRange = false;

	EntityPlayer player = (EntityPlayer) sender;

	for (int i = 0; i < amount; i++) {
	    player.addItemStackToInventory(SpellItemGen.Create(blueprint));
	}
    }
}