package com.robertx22.mine_and_slash.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.robertx22.mine_and_slash.dimensions.MapManager;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.world.dimension.DimensionType;

public class RestoreLevel {
    public static void register(CommandDispatcher<CommandSource> commandDispatcher) {
        commandDispatcher.register(Commands.literal("restorelevel")
                .executes(ctx -> run(ctx.getSource())));
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
