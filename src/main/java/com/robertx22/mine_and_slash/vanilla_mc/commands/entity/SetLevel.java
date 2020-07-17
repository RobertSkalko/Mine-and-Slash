package com.robertx22.mine_and_slash.vanilla_mc.commands.entity;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.robertx22.mine_and_slash.vanilla_mc.commands.CommandRefs;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import net.minecraft.command.CommandSource;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.PlayerEntity;

import javax.annotation.Nullable;
import java.util.Objects;

import static net.minecraft.command.Commands.argument;
import static net.minecraft.command.Commands.literal;

public class SetLevel {

    public static void register(CommandDispatcher<CommandSource> commandDispatcher) {
        commandDispatcher.register(
            literal(CommandRefs.ID)
                .then(literal("set").requires(e -> e.hasPermissionLevel(2))
                    .then(literal("level")
                        .requires(e -> e.hasPermissionLevel(2))
                        .then(argument("target", EntityArgument.player())
                            .then(argument("level", IntegerArgumentType.integer())
                                .executes(e -> execute(e.getSource(), EntityArgument.getPlayer(e, "target"), IntegerArgumentType
                                    .getInteger(e, "level"))))))));
    }

    private static int execute(CommandSource commandSource, @Nullable PlayerEntity player,
                               int lvl) {
        if (Objects.isNull(player)) {
            try {
                player = commandSource.asPlayer();
            } catch (CommandSyntaxException e) {
                e.printStackTrace();
                return 1;
            }
        }

        EntityCap.UnitData data = Load.Unit(player);

        data.setLevel(lvl, player);
        data.setExp(0);

        return 0;
    }
}