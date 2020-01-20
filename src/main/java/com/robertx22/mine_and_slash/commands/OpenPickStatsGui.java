package com.robertx22.mine_and_slash.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.network.OpenGuiPacket;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;

public class OpenPickStatsGui {
    public static void register(CommandDispatcher<CommandSource> commandDispatcher) {
        commandDispatcher.register(Commands.literal(COMMAND)
                .requires(e -> e.hasPermissionLevel(0))
                .executes(ctx -> run(ctx.getSource())));
    }

    public static final String COMMAND = "openpickstatsgui";

    private static int run(CommandSource source) {

        try {

            if (source.getEntity() instanceof ServerPlayerEntity) {

                MMORPG.sendToClient(new OpenGuiPacket(OpenGuiPacket.GuiType.PICK_STATS), (ServerPlayerEntity) source
                        .getEntity());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 1;
    }
}
