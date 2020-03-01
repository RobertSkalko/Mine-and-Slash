package com.robertx22.mine_and_slash.commands.entity;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.robertx22.mine_and_slash.commands.CommandRefs;
import com.robertx22.mine_and_slash.commands.suggestions.BossSuggestions;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import net.minecraft.command.CommandSource;
import net.minecraft.command.arguments.EntitySummonArgument;
import net.minecraft.command.arguments.SuggestionProviders;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;

import static net.minecraft.command.Commands.argument;
import static net.minecraft.command.Commands.literal;

public class SummonBoss {

    public static void register(CommandDispatcher<CommandSource> commandDispatcher) {
        commandDispatcher.register(

            literal(CommandRefs.ID)
                .then(literal("summon").requires(e -> e.hasPermissionLevel(2))
                    .then(literal("boss")
                        .requires(e -> e.hasPermissionLevel(2))
                        .then(argument("entity_type", EntitySummonArgument.entitySummon())
                            .suggests(SuggestionProviders.SUMMONABLE_ENTITIES)
                            .then(argument("boss_id", StringArgumentType.string())
                                .suggests(new BossSuggestions())
                                .executes(ctx -> run(ctx.getSource(),
                                    EntitySummonArgument.getEntityId(
                                        ctx, "entity_type"),
                                    StringArgumentType.getString(
                                        ctx, "boss_id")
                                )))))));
    }

    private static int run(CommandSource source, @Nullable ResourceLocation type, String boss_id) {

        try {
            Entity en = ForgeRegistries.ENTITIES.getValue(type)
                .create(source.getWorld());
            BlockPos p = source.getEntity()
                .getPosition();
            en.setPosition(p.getX(), p.getY(), p.getZ());
            Load.boss(en)
                .setBoss(SlashRegistry.Bosses()
                    .get(boss_id));
            source.getWorld()
                .addEntity(en);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 1;
    }
}

