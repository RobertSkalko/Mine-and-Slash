package com.robertx22.mine_and_slash.commands.entity;

import com.mojang.brigadier.CommandDispatcher;
import com.robertx22.mine_and_slash.uncommon.capability.PlayerTalentsCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.PlayerEntity;

import javax.annotation.Nullable;

public class ResetTalents {

    public static void register(CommandDispatcher<CommandSource> commandDispatcher) {
        commandDispatcher.register(Commands.literal("resettalents")
                .requires(e -> e.hasPermissionLevel(2))
                .then(Commands.argument("target", EntityArgument.entity())
                        .executes(ctx -> run(EntityArgument.getPlayer(ctx, "target")))));
    }

    private static int run(@Nullable PlayerEntity en) {

        try {

            PlayerTalentsCap.IPlayerTalentsData data = Load.talents(en);
            data.reset();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 1;
    }
}
