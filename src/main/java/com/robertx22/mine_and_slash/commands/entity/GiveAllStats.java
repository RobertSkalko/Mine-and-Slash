package com.robertx22.mine_and_slash.commands.entity;

import com.mojang.brigadier.CommandDispatcher;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;

import javax.annotation.Nullable;
import java.util.UUID;

public class GiveAllStats {

    public static void register(CommandDispatcher<CommandSource> commandDispatcher) {
        commandDispatcher.register(Commands.literal("giveallstatsfortesting")
                                           .requires(e -> e.hasPermissionLevel(2))
                                           .then(Commands.argument("target", EntityArgument.entity())

                                                         .executes(
                                                                 ctx -> run(EntityArgument.getPlayer(ctx, "target")))));
    }

    private static int run(@Nullable Entity en) {

        try {

            if (en instanceof LivingEntity) {

                EntityCap.UnitData data = Load.Unit(en);

                SlashRegistry.Stats()
                        .getList()
                        .forEach(x -> data.getCustomExactStats()
                                .add(UUID.randomUUID().toString(), x.GUID(), 100, StatTypes.Flat));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 1;
    }
}
