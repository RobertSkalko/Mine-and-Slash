package com.robertx22.mine_and_slash.commands.stats;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.robertx22.mine_and_slash.commands.CommandRefs;
import com.robertx22.mine_and_slash.commands.suggestions.StatSuggestions;
import com.robertx22.mine_and_slash.commands.suggestions.StatTypeSuggestions;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;
import net.minecraft.command.CommandSource;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;

import javax.annotation.Nullable;

import static net.minecraft.command.Commands.argument;
import static net.minecraft.command.Commands.literal;

public class GiveStat {

    public static void register(CommandDispatcher<CommandSource> commandDispatcher) {
        commandDispatcher.register(

            literal(CommandRefs.ID)
                .then(literal("stat").requires(e -> e.hasPermissionLevel(2))
                    .then(literal("exact")
                        .then(literal("give")
                            .requires(e -> e.hasPermissionLevel(2))
                            .then(argument("target", EntityArgument.entity())
                                .then(argument("statGUID", StringArgumentType.string())
                                    .suggests(new StatSuggestions())
                                    .then(argument("statType", StringArgumentType.string())
                                        .suggests(new StatTypeSuggestions())
                                        .then(argument("GUID", StringArgumentType
                                            .string())
                                            .then(argument("value", FloatArgumentType
                                                .floatArg())
                                                .executes(ctx -> run(EntityArgument
                                                    .getPlayer(ctx, "target"), StringArgumentType
                                                    .getString(ctx, "statGUID"), StringArgumentType
                                                    .getString(ctx, "statType"), StringArgumentType
                                                    .getString(ctx, "GUID"), FloatArgumentType
                                                    .getFloat(ctx, "value"))))))))))));
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
