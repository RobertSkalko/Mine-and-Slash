package com.robertx22.mine_and_slash.commands.entity;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.Entity;

import javax.annotation.Nullable;

public class SetEntityRarity {

    public static void register(CommandDispatcher<CommandSource> commandDispatcher) {
        commandDispatcher.register(Commands.literal("setentityrarity")
                .requires(e -> e.hasPermissionLevel(2))
                .then(Commands.argument("target", EntityArgument.entity())
                        .then(Commands.argument("rarity", IntegerArgumentType.integer(0, 5))
                                .executes(e -> execute(e.getSource(), EntityArgument.getEntity(e, "target"), IntegerArgumentType
                                        .getInteger(e, "rarity"))))));
    }

    private static int execute(CommandSource commandSource, @Nullable Entity player,
                               int rarity) {

        EntityCap.UnitData data = Load.Unit(player);

        data.setRarity(rarity);

        return 0;
    }
}
