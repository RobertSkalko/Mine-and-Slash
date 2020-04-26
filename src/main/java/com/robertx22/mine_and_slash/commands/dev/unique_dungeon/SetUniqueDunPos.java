package com.robertx22.mine_and_slash.commands.dev.unique_dungeon;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.robertx22.mine_and_slash.commands.CommandRefs;
import com.robertx22.mine_and_slash.commands.suggestions.PosTypeSuggestions;
import net.minecraft.command.CommandSource;
import net.minecraft.command.arguments.BlockPosArgument;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;

import static net.minecraft.command.Commands.argument;
import static net.minecraft.command.Commands.literal;

public class SetUniqueDunPos {

    public static void register(CommandDispatcher<CommandSource> commandDispatcher) {

        commandDispatcher.register(
            literal(CommandRefs.ID)
                .then(literal("dev").requires(e -> e.hasPermissionLevel(2))
                    .then(literal("unique_dungeon")
                        .then(argument("pos", BlockPosArgument.blockPos())
                            .then(argument("type", StringArgumentType.word())
                                .suggests(new PosTypeSuggestions())
                                .executes(e -> run(
                                    e.getSource(),
                                    BlockPosArgument.getBlockPos(e, "pos"),
                                    StringArgumentType.getString(e, "type")
                                )))))));
    }

    private static int run(CommandSource commandSource, BlockPos pos,
                           String word) {

        try {
            PlayerEntity player = commandSource.asPlayer();

            DunExportData.init(player);

            DunExportData.MAP.get(player)
                .set(DunExportData.PosType.valueOf(word), pos);

        } catch (CommandSyntaxException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
