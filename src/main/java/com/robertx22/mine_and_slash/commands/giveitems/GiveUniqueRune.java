package com.robertx22.mine_and_slash.commands.giveitems;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.robertx22.mine_and_slash.commands.CommandRefs;
import com.robertx22.mine_and_slash.commands.suggestions.UniqueRuneSuggestions;
import com.robertx22.mine_and_slash.loot.blueprints.UniqueRuneBlueprint;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import net.minecraft.command.CommandSource;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.PlayerEntity;

import javax.annotation.Nullable;
import java.util.Objects;

import static net.minecraft.command.Commands.argument;
import static net.minecraft.command.Commands.literal;

public class GiveUniqueRune {

    public static void register(CommandDispatcher<CommandSource> commandDispatcher) {
        commandDispatcher.register(
            literal(CommandRefs.ID)
                .then(literal("give").requires(e -> e.hasPermissionLevel(2))
                    .then(literal("unique_rune")
                        .then(argument("target", EntityArgument.player())
                            .then(argument("uniqueID", StringArgumentType.word())
                                .suggests(new UniqueRuneSuggestions())
                                .then(argument("level", IntegerArgumentType.integer())
                                    .then(argument("amount", IntegerArgumentType
                                        .integer(1, 5000))
                                        .executes(e -> execute(e.getSource(), EntityArgument
                                            .getPlayer(e, "target"), StringArgumentType
                                            .getString(e, "uniqueID"), IntegerArgumentType
                                            .getInteger(e, "level"), IntegerArgumentType
                                            .getInteger(e, "amount")

                                        )))))))));
    }

    private static int execute(CommandSource commandSource, @Nullable PlayerEntity player,
                               String id, int lvl, int amount) {

        if (Objects.isNull(player)) {
            try {
                player = commandSource.asPlayer();
            } catch (CommandSyntaxException e) {
                e.printStackTrace();
                return 1;
            }
        }
        for (int i = 0; i < amount; i++) {

            UniqueRuneBlueprint blueprint = new UniqueRuneBlueprint(lvl, 0);

            blueprint.uniqueRunePart.set(SlashRegistry.Runes()
                .get(id));

            blueprint.level.LevelRange = false;

            player.addItemStackToInventory(blueprint.createStack());
        }

        return 0;
    }
}
