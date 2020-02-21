package com.robertx22.mine_and_slash.commands.stats;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.robertx22.mine_and_slash.commands.CommandRefs;
import com.robertx22.mine_and_slash.commands.suggestions.StatModSuggestions;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import net.minecraft.command.CommandSource;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;

import javax.annotation.Nullable;

import static net.minecraft.command.Commands.argument;
import static net.minecraft.command.Commands.literal;

public class GiveStatMod {

    public static void register(CommandDispatcher<CommandSource> commandDispatcher) {
        commandDispatcher.register(

            literal(CommandRefs.ID)
                .requires(e -> e.hasPermissionLevel(2))
                .then(literal("stat")
                    .then(literal("mods")
                        .then(literal("give")
                            .requires(e -> e.hasPermissionLevel(2))
                            .then(argument("target", EntityArgument.entity())
                                .then(argument("statGUID", StringArgumentType.string())
                                    .suggests(new StatModSuggestions())
                                    .then(argument("GUID", StringArgumentType.string())
                                        .then(argument("percent",
                                            IntegerArgumentType.integer()
                                        )
                                            .executes(
                                                ctx -> run(EntityArgument.getPlayer(
                                                    ctx, "target"),
                                                    StringArgumentType.getString(
                                                        ctx, "statGUID"),
                                                    StringArgumentType.getString(
                                                        ctx, "GUID"),
                                                    IntegerArgumentType.getInteger(
                                                        ctx, "percent")
                                                ))))))))));
    }

    private static int run(@Nullable Entity en, String statGUID, String GUID, int percent) {

        try {

            if (en instanceof LivingEntity) {

                EntityCap.UnitData data = Load.Unit(en);

                data.getCustomStats()
                    .add(GUID, statGUID, percent);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 1;
    }
}
