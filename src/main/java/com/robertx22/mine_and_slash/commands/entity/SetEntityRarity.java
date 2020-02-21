package com.robertx22.mine_and_slash.commands.entity;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.robertx22.mine_and_slash.commands.CommandRefs;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import net.minecraft.command.CommandSource;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.Entity;

import javax.annotation.Nullable;

import static net.minecraft.command.Commands.argument;
import static net.minecraft.command.Commands.literal;

public class SetEntityRarity {

    public static void register(CommandDispatcher<CommandSource> commandDispatcher) {
        commandDispatcher.register(

            literal(CommandRefs.ID)
                .requires(e -> e.hasPermissionLevel(2))
                .then(literal("set")
                    .then(literal("entity")
                        .then(literal("rarity")
                            .requires(e -> e.hasPermissionLevel(2))
                            .then(argument("target", EntityArgument.entity())
                                .then(argument("rarity", IntegerArgumentType.integer(0, 5))
                                    .executes(e -> execute(e.getSource(), EntityArgument.getEntity(e, "target"), IntegerArgumentType
                                        .getInteger(e, "rarity")))))))));
    }

    private static int execute(CommandSource commandSource, @Nullable Entity player,
                               int rarity) {

        EntityCap.UnitData data = Load.Unit(player);

        data.setRarity(rarity);

        return 0;
    }
}
