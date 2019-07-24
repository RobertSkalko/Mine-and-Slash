package com.robertx22.mine_and_slash.commands.entity;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;

import javax.annotation.Nullable;

public class SetEntityLevel {
    public static void register(CommandDispatcher<CommandSource> commandDispatcher) {
        commandDispatcher.register(Commands.literal("setentitylevel")
                .requires(e -> e.hasPermissionLevel(2))
                .then(Commands.argument("target", EntityArgument.entity())
                        .then(Commands.argument("level", IntegerArgumentType.integer())
                                .executes(e -> execute(e.getSource(), EntityArgument.getEntity(e, "target"), IntegerArgumentType
                                        .getInteger(e, "level"))))));
    }

    private static int execute(CommandSource commandSource, @Nullable Entity entity,
                               int lvl) {

        LivingEntity living = (LivingEntity) entity;

        EntityCap.UnitData data = Load.Unit(living);

        data.setLevel(lvl, living);
        data.setExp(0);

        return 0;
    }
}
