package com.robertx22.uncommon.commands;

import com.robertx22.database.rarities.items.Unique;
import com.robertx22.generation.UniqueGearGen;
import com.robertx22.generation.blueprints.GearBlueprint;

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
		return "/giveunique (lvl),  (type: Sword, Necklace etc), (tier), (amount)";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {

		int lvl = Integer.valueOf(args[0]);
		String type = args[1];
		int tier = Integer.valueOf(args[2]);
		int amount = Integer.valueOf(args[3]);

		GearBlueprint blueprint = new GearBlueprint(lvl);

		blueprint.SetSpecificRarity(new Unique().Rank());
		blueprint.SetSpecificType(type);
		blueprint.tier = tier;

		EntityPlayer player = (EntityPlayer) sender;

		for (int i = 0; i < amount; i++) {
			player.addItemStackToInventory(UniqueGearGen.CreateStack(blueprint));
		}

	}
}