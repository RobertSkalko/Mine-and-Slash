package com.robertx22.mine_and_slash.commands.giveitems;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.robertx22.mine_and_slash.commands.suggestions.RunewordSuggestions;
import com.robertx22.mine_and_slash.loot.blueprints.AwakenRuneWordBlueprint;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import net.minecraft.command.CommandSource;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.PlayerEntity;

import javax.annotation.Nullable;
import java.util.Objects;

import static net.minecraft.command.Commands.argument;
import static net.minecraft.command.Commands.literal;

public class GiveAwakenRuneword {

    public static void register(CommandDispatcher<CommandSource> commandDispatcher) {

        commandDispatcher.register(literal("slash")
            .then(literal("give").requires(e -> e.hasPermissionLevel(2))
                .then(literal("rune_word")
                    .then(argument("target", EntityArgument.player())
                        .then(argument("type", StringArgumentType.word())
                            .suggests(new RunewordSuggestions())
                            .then(argument("amount", IntegerArgumentType.integer(1, 30000))
                                .executes(e -> run(e.getSource(), EntityArgument.getPlayer(e, "target"), StringArgumentType
                                    .getString(e, "type"), IntegerArgumentType
                                    .getInteger(e, "amount")

                                ))))))));
    }

    private static int run(CommandSource commandSource, @Nullable PlayerEntity player,
                           String word, int amount) {

        if (Objects.isNull(player)) {
            try {
                player = commandSource.asPlayer();
            } catch (CommandSyntaxException e) {
                e.printStackTrace();
                return 1;
            }
        }

        for (int i = 0; i < amount; i++) {
            AwakenRuneWordBlueprint blueprint = new AwakenRuneWordBlueprint();
            if (word != "random") {

                blueprint.runeWord.set(SlashRegistry.RuneWords()
                    .get(word));

            }

            try {
                player.addItemStackToInventory(blueprint.createStack());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return 0;
    }
}
