package com.robertx22.mine_and_slash.commands.reset;

import com.mojang.brigadier.CommandDispatcher;
import com.robertx22.mine_and_slash.commands.CommandRefs;
import com.robertx22.mine_and_slash.uncommon.capability.PlayerTalentsCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import net.minecraft.command.CommandSource;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.PlayerEntity;

import javax.annotation.Nullable;

import static net.minecraft.command.Commands.argument;
import static net.minecraft.command.Commands.literal;

public class ResetTalents {

    public static void register(CommandDispatcher<CommandSource> commandDispatcher) {
        commandDispatcher.register(
            literal(CommandRefs.ID)
                .requires(e -> e.hasPermissionLevel(2))
                .then(literal("reset")
                    .then(literal("talents")
                        .then(argument("target", EntityArgument.entity())
                            .executes(ctx -> run(EntityArgument.getPlayer(ctx, "target")))))));
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
