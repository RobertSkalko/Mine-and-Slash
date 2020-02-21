package com.robertx22.mine_and_slash.commands.party;

import com.mojang.brigadier.CommandDispatcher;
import com.robertx22.mine_and_slash.uncommon.capability.server_wide.TeamCap;
import com.robertx22.mine_and_slash.uncommon.wrappers.SText;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.ServerPlayerEntity;

import static net.minecraft.command.Commands.literal;

public class PartyCommand {

    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(
            literal("slash")
                .then(
                    literal("party")
                        .then(literal("list").executes(c -> {

                            ServerPlayerEntity player = c.getSource()
                                .asPlayer();
                            player
                                .sendMessage(new SText("Party List: " + TeamCap.getCapability()
                                    .getPlayersInTeam(player)));
                            return 0;
                        }))
                        .then(literal("create").executes(c -> {

                            ServerPlayerEntity player = c.getSource()
                                .asPlayer();
                            TeamCap.getCapability()
                                .createTeam(player);
                            return 0;
                        }))
                        .then(literal("leave").executes(c -> {

                            ServerPlayerEntity player = c.getSource()
                                .asPlayer();
                            TeamCap.getCapability()
                                .leaveTeam(player);
                            return 0;
                        }))
                        .then(literal("join").then(Commands.argument("target", EntityArgument.player())
                            .executes(c -> {
                                ServerPlayerEntity player = c.getSource()
                                    .asPlayer();
                                ServerPlayerEntity player2 = EntityArgument.getPlayer(c, "target");

                                TeamCap.ITeamData cap = TeamCap.getCapability();
                                cap.joinTeam(player, cap.getTeamId(player2));

                                return 0;
                            })))
                        .then(literal("invite").then(Commands.argument("target", EntityArgument.player())
                            .executes(c -> {
                                ServerPlayerEntity player = c.getSource()
                                    .asPlayer();
                                ServerPlayerEntity player2 = EntityArgument.getPlayer(c, "target");
                                TeamCap.ITeamData cap = TeamCap.getCapability();
                                cap.invite(player2, cap.getTeamId(player));

                                return 0;
                            })))
                )
        );

    }

}
