package com.robertx22.mine_and_slash.commands.stats;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
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

public class RemoveStatMod {

    public static void register(CommandDispatcher<CommandSource> commandDispatcher) {
        commandDispatcher.register(
            literal(CommandRefs.ID)
                .then(literal("stat").requires(e -> e.hasPermissionLevel(2))
                    .then(literal("mods")
                        .then(literal("remove")
                            .requires(e -> e.hasPermissionLevel(2))
                            .then(argument("target", EntityArgument.entity())
                                .then(argument("GUID", StringArgumentType.string())
                                    .executes(ctx -> run(EntityArgument.getPlayer(ctx, "target"), StringArgumentType
                                        .getString(ctx, "GUID")))))))));
    }

    private static int run(@Nullable Entity en, String GUID) {

        try {

            if (en instanceof LivingEntity) {

                EntityCap.UnitData data = Load.Unit(en);

                data.getCustomStats()
                    .remove(GUID);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 1;
    }
}
