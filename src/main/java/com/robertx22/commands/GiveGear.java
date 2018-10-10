package com.robertx22.commands;

import com.robertx22.item.GearCreator;
import com.robertx22.mmorpg.ModConfig;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

public class GiveGear extends CommandBase {

    @Override
    public String getName() {
        return "givegear";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/givegear (lvl), (rarity 0-4), (type: ARMOR, WEAPON etc)";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {

        if (ModConfig.Cheats.CHEAT_MODE) {

            int lvl = Integer.valueOf(args[0]);
            int rarity = Integer.valueOf(args[1]);
            String type = args[2];

            ItemStack item = GearCreator.createGear(lvl, rarity, type);

            EntityPlayer player = (EntityPlayer) sender;

            player.addItemStackToInventory(item);
        }
        else {
            sender.sendMessage(new TextComponentString("You have to enable Cheats in mod config to use commands!"));
        }
    }
}