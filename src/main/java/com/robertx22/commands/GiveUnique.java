package com.robertx22.commands;

import com.robertx22.database.rarities.items.UniqueItem;
import com.robertx22.generation.UniqueGearGen;
import com.robertx22.generation.blueprints.UniqueBlueprint;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;

public class GiveUnique extends CommandBase {

    @Override
    public String getName() {
	return "giveunique";
    }

    @Override
    public String getUsage(ICommandSender sender) {
	return "/giveunique (player) (lvl) (type: Sword, Necklace etc) (tier) (amount) NOTE: It's Caps sensitive! Sword, not sword.";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
    	if (args.length < 5) throw new WrongUsageException("/giveunique (player) (lvl) (type: Sword, Necklace etc) (tier) (amount) NOTE: It's Caps sensitive! Sword, not sword.");
    	
    	int lvl = Integer.valueOf(args[1]);
    	String type = args[2];
    	int tier = Integer.valueOf(args[3]);
    	int amount = Integer.valueOf(args[4]);

    	UniqueBlueprint blueprint = new UniqueBlueprint(lvl, tier, true);
    	blueprint.SetSpecificRarity(new UniqueItem().Rank());
    	blueprint.SetSpecificType(type);
    	blueprint.LevelRange = false;

    	EntityPlayer player = getPlayer(server, sender, args[0]);

    	for (int i = 0; i < amount; i++) {
    		player.addItemStackToInventory(UniqueGearGen.CreateStack(blueprint));
    	}

    }
}