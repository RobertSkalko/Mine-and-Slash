package com.robertx22.mine_and_slash.commands.dev.unique_dungeon;

import com.google.common.base.Preconditions;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.robertx22.mine_and_slash.commands.CommandRefs;
import net.minecraft.command.CommandSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.ChunkPos;

import java.util.List;

import static net.minecraft.command.Commands.argument;
import static net.minecraft.command.Commands.literal;

public class ExportUniqueDungeon {

    public static void register(CommandDispatcher<CommandSource> commandDispatcher) {

        commandDispatcher.register(
            literal(CommandRefs.ID)
                .then(literal("dev").requires(e -> e.hasPermissionLevel(2))
                    .then(literal("unique_dungeon")
                        .then(literal("export")
                            .then(argument("name", StringArgumentType.word())
                                .then(argument("level", IntegerArgumentType.integer())
                                    .executes(e -> run(
                                        e.getSource(),
                                        IntegerArgumentType.getInteger(e, "level"),
                                        StringArgumentType.getString(e, "name")
                                    ))))))));
    }

    private static int run(CommandSource commandSource, int level,
                           String word) {

        try {
            PlayerEntity player = commandSource.asPlayer();

            DunExportData data = DunExportData.MAP.get(player);

            Preconditions.checkNotNull(data);
            Preconditions.checkNotNull(data.entrancePos);
            Preconditions.checkNotNull(data.firstPos);
            Preconditions.checkNotNull(data.lastPos);

            List<ChunkPos> list = data.getAllChunks();

            int a = 5;

        } catch (CommandSyntaxException e) {
            e.printStackTrace();
        }

        return 0;
    }
}

