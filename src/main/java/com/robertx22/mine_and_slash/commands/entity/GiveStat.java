package com.robertx22.mine_and_slash.commands.entity;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.robertx22.mine_and_slash.commands.bases.StatSuggestions;
import com.robertx22.mine_and_slash.commands.bases.StatTypeSuggestions;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;

import javax.annotation.Nullable;

public class GiveStat {

    public static void register(CommandDispatcher<CommandSource> commandDispatcher) {
        commandDispatcher.register(Commands.literal("giveexactstat")
                .requires(e -> e.hasPermissionLevel(2))
                .then(Commands.argument("target", EntityArgument.entity())
                        .then(Commands.argument("statGUID", StringArgumentType.string())
                                .suggests(new StatSuggestions())
                                .then(Commands.argument("statType", StringArgumentType.string())
                                        .suggests(new StatTypeSuggestions())
                                        .then(Commands.argument("GUID", StringArgumentType
                                                .string())
                                                .then(Commands.argument("value", FloatArgumentType
                                                        .floatArg())
                                                        .executes(ctx -> run(EntityArgument
                                                                .getPlayer(ctx, "target"), StringArgumentType
                                                                .getString(ctx, "statGUID"), StringArgumentType
                                                                .getString(ctx, "statType"), StringArgumentType
                                                                .getString(ctx, "GUID"), FloatArgumentType
                                                                .getFloat(ctx, "value")))))))));
    }

    private static int run(@Nullable Entity en, String statGUID, String statType,
                           String GUID, float value) {

        try {

            if (en instanceof LivingEntity) {
                EntityCap.UnitData data = Load.Unit(en);
                data.getCustomExactStats()
                        .add(GUID, statGUID, value, StatModTypes.valueOf(statType));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 1;
    }
}
