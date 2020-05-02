package com.robertx22.mine_and_slash.commands.dev.unique_dungeon;

import com.google.common.base.Preconditions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.robertx22.mine_and_slash.commands.CommandRefs;
import com.robertx22.mine_and_slash.data_generation.unique_dungeons.UniqueDungeon;
import net.minecraft.command.CommandSource;
import net.minecraft.data.DirectoryCache;
import net.minecraft.data.IDataProvider;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraftforge.fml.loading.FMLPaths;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

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

    public static final Gson GSON = (new GsonBuilder()).setPrettyPrinting()
        .create();

    private static int run(CommandSource commandSource, int level,
                           String word) {

        try {
            PlayerEntity player = commandSource.asPlayer();

            DunExportData data = DunExportData.MAP.get(player);

            Preconditions.checkNotNull(data);
            Preconditions.checkNotNull(data.entrancePos);
            Preconditions.checkNotNull(data.firstPos);
            Preconditions.checkNotNull(data.lastPos);

            data.exportStructureFiles(player, word);

            List<ChunkPos> list = ChunkPos.getAllInBox(new ChunkPos(data.firstPos), new ChunkPos(data.lastPos))
                .collect(Collectors.toList());

            BlockPos normEntrance = new BlockPos(data.entrancePos.getX() - 16 * data.getSmallestX(list), data.entrancePos.getY() - data.getStartY(), data.entrancePos.getZ() - 16 * data.getSmallestZ(list));

            UniqueDungeon dun = new UniqueDungeon(word, level, normEntrance, 0);

            Path target = FMLPaths.GAMEDIR.get()
                .resolve(dun.GUID() + ".json");
            try {
                IDataProvider.save(GSON, new DirectoryCache(FMLPaths.GAMEDIR.get(), "cache"), dun.toJson(), target);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } catch (CommandSyntaxException e) {
            e.printStackTrace();
        }

        return 0;
    }
}

