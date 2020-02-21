package com.robertx22.mine_and_slash.commands.party;

import com.mojang.brigadier.CommandDispatcher;
import com.robertx22.mine_and_slash.uncommon.wrappers.SText;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.PlayerEntity;

import static net.minecraft.command.Commands.literal;

public class PartyCommand {

    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(
            literal("slash")
                .then(
                    literal("party")
                        .then(literal("list").executes(c -> {
                            c.getSource()
                                .asPlayer()
                                .sendMessage(new SText("Party List: "));
                            return 0;
                        }))
                        .then(literal("join").then(Commands.argument("target", EntityArgument.player())
                            .executes(c -> {
                                PlayerEntity player = EntityArgument.getPlayer(c, "target");

                                return 0;
                            })))
                        .then(literal("invite").then(Commands.argument("target", EntityArgument.player())
                            .executes(c -> {
                                PlayerEntity player = EntityArgument.getPlayer(c, "target");
                                return 0;
                            })))
                )
        );

    }

}
