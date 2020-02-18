package com.robertx22.mine_and_slash.commands.entity;

import com.mojang.brigadier.CommandDispatcher;
import com.robertx22.mine_and_slash.uncommon.capability.PlayerSpellCap;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.PlayerEntity;

import javax.annotation.Nullable;

public class ResetSpellCooldowns {
    public static void register(CommandDispatcher<CommandSource> commandDispatcher) {
        commandDispatcher.register(Commands.literal("resetspellcooldowns")
            .requires(e -> e.hasPermissionLevel(2))
            .then(Commands.argument("target", EntityArgument.entity())
                .executes(
                    ctx -> run(EntityArgument.getPlayer(ctx, "target")))));
    }

    private static int run(@Nullable PlayerEntity en) {

        try {
            en.getCapability(PlayerSpellCap.Data)
                .ifPresent(x -> x.getSpellData()
                    .onTimePass(en, x, 50000));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 1;
    }
}
