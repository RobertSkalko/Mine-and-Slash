package com.robertx22.mine_and_slash.commands.stats;

import com.mojang.brigadier.CommandDispatcher;
import com.robertx22.mine_and_slash.commands.CommandRefs;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import net.minecraft.command.CommandSource;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;

import javax.annotation.Nullable;

import static net.minecraft.command.Commands.argument;
import static net.minecraft.command.Commands.literal;

public class ClearStatMods {

    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(
            literal(CommandRefs.ID)
                .then(literal("stat").requires(e -> e.hasPermissionLevel(2))
                    .then(literal("mods")
                        .then(literal("clear")
                            .requires(e -> e.hasPermissionLevel(2))
                            .then(argument("target", EntityArgument.entity())
                                .executes(ctx -> run(EntityArgument.getPlayer(ctx, "target"))))))));
    }

    private static int run(@Nullable Entity en) {

        try {

            if (en instanceof LivingEntity) {
                EntityCap.UnitData data = Load.Unit(en);
                data.getCustomStats().stats.clear();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 1;
    }
}
