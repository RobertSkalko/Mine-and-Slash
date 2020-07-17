package com.robertx22.mine_and_slash.vanilla_mc.commands.open_gui;

import com.mojang.brigadier.CommandDispatcher;
import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.vanilla_mc.commands.CommandRefs;
import com.robertx22.mine_and_slash.vanilla_mc.packets.OpenGuiPacket;
import net.minecraft.command.CommandSource;
import net.minecraft.entity.player.ServerPlayerEntity;

import static net.minecraft.command.Commands.literal;

public class OpenHub {
    public static void register(CommandDispatcher<CommandSource> commandDispatcher) {
        commandDispatcher.register(
            literal(CommandRefs.ID)
                .then(literal("open").requires(e -> e.hasPermissionLevel(0))
                    .then(literal("hub")
                        .executes(ctx -> run(ctx.getSource())))));
    }

    public static final String COMMAND = "slash open hub";

    private static int run(CommandSource source) {

        try {

            if (source.getEntity() instanceof ServerPlayerEntity) {
                MMORPG.sendToClient(
                    new OpenGuiPacket(OpenGuiPacket.GuiType.MAIN_HUB), (ServerPlayerEntity) source.getEntity());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 1;
    }
}

