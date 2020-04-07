package com.robertx22.mine_and_slash.commands.giveitems;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.robertx22.mine_and_slash.commands.suggestions.AbilitySuggestions;
import com.robertx22.mine_and_slash.saveclasses.spells.IAbility;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import net.minecraft.command.CommandSource;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.PlayerEntity;

import javax.annotation.Nullable;
import java.util.Objects;

import static net.minecraft.command.Commands.argument;
import static net.minecraft.command.Commands.literal;

public class GiveAbilityLevels {

    public static void register(CommandDispatcher<CommandSource> commandDispatcher) {

        commandDispatcher.register(literal("slash")
            .then(literal("set").requires(e -> e.hasPermissionLevel(2))
                .then(literal("ability")
                    .then(argument("target", EntityArgument.player())
                        .then(argument("type", StringArgumentType.word())
                            .suggests(new AbilitySuggestions())
                            .then(argument("levels", IntegerArgumentType.integer(1, 30000))
                                .executes(e -> run(e.getSource(), EntityArgument.getPlayer(e, "target"), StringArgumentType
                                    .getString(e, "type"), IntegerArgumentType
                                    .getInteger(e, "levels")

                                ))))))));
    }

    private static int run(CommandSource commandSource, @Nullable PlayerEntity player,
                           String id, int amount) {

        if (Objects.isNull(player)) {
            try {
                player = commandSource.asPlayer();
            } catch (CommandSyntaxException e) {
                e.printStackTrace();
                return 1;
            }
        }

        Load.spells(player)
            .getAbilitiesData()
            .setLevel(IAbility.fromId(id), amount);

        return 0;
    }
}
