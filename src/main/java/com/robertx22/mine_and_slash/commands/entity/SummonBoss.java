package com.robertx22.mine_and_slash.commands.entity;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.robertx22.mine_and_slash.commands.bases.BossSuggestions;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntitySummonArgument;
import net.minecraft.command.arguments.SuggestionProviders;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;

public class SummonBoss {
    public static void register(CommandDispatcher<CommandSource> commandDispatcher) {
        commandDispatcher.register(Commands.literal("summonboss")
                                           .requires(e -> e.hasPermissionLevel(2))
                                           .then(Commands.argument("entity_type", EntitySummonArgument.entitySummon())
                                                         .suggests(SuggestionProviders.SUMMONABLE_ENTITIES)
                                                         .then(Commands.argument("boss_id", StringArgumentType.string())
                                                                       .suggests(new BossSuggestions())
                                                                       .executes(ctx -> run(ctx.getSource(),
                                                                                            EntitySummonArgument.getEntityId(
                                                                                                    ctx, "entity_type"),
                                                                                            StringArgumentType.getString(
                                                                                                    ctx, "boss_id")
                                                                       )))));
    }

    private static int run(CommandSource source, @Nullable ResourceLocation type, String boss_id) {

        try {
            Entity en = ForgeRegistries.ENTITIES.getValue(type).create(source.getWorld());
            BlockPos p = source.getEntity().getPosition();
            en.setPosition(p.getX(), p.getY(), p.getZ());
            Load.boss(en).setBoss(SlashRegistry.Bosses().get(boss_id));
            source.getWorld().addEntity(en);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 1;
    }
}

