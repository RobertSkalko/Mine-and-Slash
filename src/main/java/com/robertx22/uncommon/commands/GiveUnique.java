package com.robertx22.uncommon.commands;

import com.robertx22.database.rarities.items.UniqueItem;
import com.robertx22.generation.UniqueGearGen;
import com.robertx22.generation.blueprints.UniqueBlueprint;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;

public class GiveUnique extends CommandBase {

    @Override
    public String getName() {
	return "giveunique";
    }

    @Override
    public String getUsage(ICommandSender sender) {
	return "/giveunique (lvl),  (type: Sword, Necklace etc), (tier), (amount) NOTE: It's Caps sensitive! Sword, not sword.";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {

	int lvl = Integer.valueOf(args[0]);
	String type = args[1];
	int tier = Integer.valueOf(args[2]);
	int amount = Integer.valueOf(args[3]);

	UniqueBlueprint blueprint = new UniqueBlueprint(lvl, tier, true);
	blueprint.SetSpecificRarity(new UniqueItem().Rank());
	blueprint.SetSpecificType(type);
	blueprint.LevelRange = false;

	EntityPlayer player = (EntityPlayer) sender;

	for (int i = 0; i < amount; i++) {
	    player.addItemStackToInventory(UniqueGearGen.CreateStack(blueprint));
	}

    }
}