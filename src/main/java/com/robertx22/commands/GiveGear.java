package com.robertx22.commands;

import com.robertx22.generation.GearGen;
import com.robertx22.generation.GearGenSchema;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;

public class GiveGear extends CommandBase {

	@Override
	public String getName() {
		return "givegear";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "/givegear (lvl), (rarity 0-4), (type: ARMOR, WEAPON etc), (amount)";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {

		// if (ModConfig.Cheats.CHEAT_MODE) {

		int lvl = Integer.valueOf(args[0]);
		int rarity = Integer.valueOf(args[1]);
		String type = args[2];
		int amount = Integer.valueOf(args[3]);

		GearGenSchema schema = new GearGenSchema(lvl);
		if (rarity > -1) {
			schema.SetSpecificRarity(rarity);
		}
		if (type != "null") {
			schema.SetSpecificType(type);
		}
		EntityPlayer player = (EntityPlayer) sender;

		for (int i = 0; i < amount; i++) {
			player.addItemStackToInventory(GearGen.Create(schema));
		}
		// } else {
		// sender.sendMessage(new TextComponentString("You have to enable Cheats in mod
		// config to use commands!"));
		// }
	}
}