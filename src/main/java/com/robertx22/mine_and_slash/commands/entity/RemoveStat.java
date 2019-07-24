package com.robertx22.mine_and_slash.commands.entity;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;

import javax.annotation.Nullable;

public class RemoveStat {

    public static void register(CommandDispatcher<CommandSource> commandDispatcher) {
        commandDispatcher.register(Commands.literal("removestat")
                .requires(e -> e.hasPermissionLevel(2))
                .then(Commands.argument("target", EntityArgument.entity())
                        .then(Commands.argument("GUID", StringArgumentType.string())
                                .executes(ctx -> run(EntityArgument.getPlayer(ctx, "target"), StringArgumentType
                                        .getString(ctx, "GUID"))))));
    }

    private static int run(@Nullable Entity en, String GUID) {

        try {

            if (en instanceof LivingEntity) {

                EntityCap.UnitData data = Load.Unit(en);

                data.getCustomStats().remove(GUID);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 1;
    }
}
