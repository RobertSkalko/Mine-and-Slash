package com.robertx22.mine_and_slash.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ConfigRegister;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;

public class ReloadConfigs {
    public static void register(CommandDispatcher<CommandSource> commandDispatcher) {
        commandDispatcher.register(Commands.literal("reloadmineslashconfigs")
                .requires(e -> e.hasPermissionLevel(2))
                .executes(ctx -> run(ctx.getSource())));
    }

    private static int run(CommandSource source) {

        try {
            ConfigRegister.regAndLoadNonForgeConfigs();

            if (source.getEntity() instanceof ServerPlayerEntity) {
                ((ServerPlayerEntity) source.getEntity()).sendMessage(new StringTextComponent("Configs reloaded"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 1;
    }
}
