package com.robertx22.mine_and_slash.commands.misc;

import com.mojang.brigadier.CommandDispatcher;
import com.robertx22.mine_and_slash.commands.CommandRefs;
import com.robertx22.mine_and_slash.dimensions.MapManager;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import net.minecraft.command.CommandSource;
import net.minecraft.world.dimension.DimensionType;

import static net.minecraft.command.Commands.literal;

public class RestoreLevel {
    public static void register(CommandDispatcher<CommandSource> commandDispatcher) {
        commandDispatcher.register(

            literal(CommandRefs.ID)
                .requires(e -> e.hasPermissionLevel(0))
                .then(literal("backup")
                    .then(literal("restore")
                        .then(literal("level")
                            .executes(ctx -> run(ctx.getSource()))))));
    }

    private static int run(CommandSource source) {

        try {
            Load.playersCapBackup(MapManager.getWorld(DimensionType.OVERWORLD))
                .getBackup()
                .restoreFromBackup(source.asPlayer());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 1;
    }
}
