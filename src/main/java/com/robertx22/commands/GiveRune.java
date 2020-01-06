package com.robertx22.commands;

import com.robertx22.generation.RuneGen;
import com.robertx22.generation.blueprints.RuneBlueprint;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;

public class GiveRune extends CommandBase {

	@Override
	public String getName() {
		return "giverune";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "/giverune (player) (lvl), (rarity 0-5), (amount)";
	}
	
	@Override
	public int getRequiredPermissionLevel() {
		return 2;
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {

		if (args.length < 4)
			throw new WrongUsageException("/giverune (player) (lvl) (rarity 0-5) (amount)");
		int lvl = Integer.valueOf(args[1]);
		int rarity = Integer.valueOf(args[2]);
		int amount = Integer.valueOf(args[3]);

		for (int i = 0; i < amount; i++) {
			RuneBlueprint blueprint = new RuneBlueprint(lvl);
			if (rarity > -1) {
				blueprint.SetSpecificRarity(rarity);
			}

			blueprint.LevelRange = false;

			EntityPlayer player = getPlayer(server, sender, args[0]);

			player.addItemStackToInventory(RuneGen.Create(blueprint));
		}

	}
}