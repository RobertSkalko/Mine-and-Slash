package com.robertx22.mine_and_slash.commands.entity;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.robertx22.mine_and_slash.commands.bases.StatModSuggestions;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;

import javax.annotation.Nullable;

public class GiveStat {
    public static void register(CommandDispatcher<CommandSource> commandDispatcher) {
        commandDispatcher.register(Commands.literal("givestat")
                .requires(e -> e.hasPermissionLevel(2))
                .then(Commands.argument("target", EntityArgument.entity())
                        .then(Commands.argument("statGUID", StringArgumentType.string())
                                .suggests(new StatModSuggestions())
                                .then(Commands.argument("GUID", StringArgumentType.string())
                                        .then(Commands.argument("percent", IntegerArgumentType
                                                .integer())
                                                .executes(ctx -> run(EntityArgument.getPlayer(ctx, "target"), StringArgumentType
                                                        .getString(ctx, "statGUID"), StringArgumentType
                                                        .getString(ctx, "GUID"), IntegerArgumentType
                                                        .getInteger(ctx, "percent"))))))));
    }

    private static int run(@Nullable Entity en, String statGUID, String GUID,
                           int percent) {

        try {

            if (en instanceof LivingEntity) {

                EntityCap.UnitData data = Load.Unit(en);

                data.getCustomStats().add(GUID, statGUID, percent, 1);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 1;
    }
}
