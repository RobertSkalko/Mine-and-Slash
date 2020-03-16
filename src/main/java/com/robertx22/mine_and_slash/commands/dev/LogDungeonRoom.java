package com.robertx22.mine_and_slash.commands.dev;

import com.mojang.brigadier.CommandDispatcher;
import com.robertx22.mine_and_slash.commands.CommandRefs;
import com.robertx22.mine_and_slash.new_content.BuiltRoom;
import com.robertx22.mine_and_slash.new_content.building.DungeonBuilder;
import com.robertx22.mine_and_slash.uncommon.wrappers.SText;
import net.minecraft.command.CommandSource;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.ChunkPos;

import javax.annotation.Nullable;

import static net.minecraft.command.Commands.argument;
import static net.minecraft.command.Commands.literal;

public class LogDungeonRoom {
    public static void register(CommandDispatcher<CommandSource> commandDispatcher) {
        commandDispatcher.register(
            literal(CommandRefs.ID)
                .then(literal("dev").requires(e -> e.hasPermissionLevel(2))
                    .then(literal("log")
                        .requires(e -> e.hasPermissionLevel(2))
                        .then(literal("dungeon_room")
                            .then(argument("target", EntityArgument.player())
                                .executes(ctx -> run(EntityArgument.getPlayer(ctx, "target"))))))));
    }

    private static int run(@Nullable PlayerEntity player) {

        DungeonBuilder builder = new DungeonBuilder(player.world.getSeed(), new ChunkPos(player.getPosition()));
        builder.build();
        BuiltRoom room = builder.dungeon.getRoomForChunk(new ChunkPos(player.getPosition()));

        player.sendMessage(new SText(room.room.group.folder + ": " + room.getStructure()
            .toString()));

        return 1;
    }
}
