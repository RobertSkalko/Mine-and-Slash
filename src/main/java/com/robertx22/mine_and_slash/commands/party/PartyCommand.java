package com.robertx22.mine_and_slash.commands.party;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.robertx22.mine_and_slash.uncommon.capability.server_wide.TeamCap;
import com.robertx22.mine_and_slash.uncommon.wrappers.SText;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.ITextComponent;

import java.util.List;
import java.util.stream.Collectors;

import static net.minecraft.command.Commands.literal;

public class PartyCommand {

    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(
            literal("slash")
                .then(
                    literal("party").requires(e -> e.hasPermissionLevel(0))
                        .then(literal("list").executes(c -> {

                            try {
                                ServerPlayerEntity player = c.getSource()
                                    .asPlayer();
                                if (TeamCap.getCapability()
                                    .isPlayerInATeam(player)) {
                                    ITextComponent text = new SText("Party List: ");
                                    List<ITextComponent> list = TeamCap.getCapability()
                                        .getPlayersInTeam(player)
                                        .stream()
                                        .map(x -> x.getDisplayName())
                                        .collect(Collectors.toList());
                                    list.forEach(x -> text.appendSibling(x));

                                    player
                                        .sendMessage(text);
                                } else {
                                    player
                                        .sendMessage(new SText("You aren't in a team."));
                                }
                            } catch (CommandSyntaxException e) {
                                e.printStackTrace();
                            }
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
