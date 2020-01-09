package com.robertx22.mine_and_slash.commands.giveitems;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.robertx22.mine_and_slash.commands.bases.RuneItemSuggestions;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.loot.blueprints.RuneBlueprint;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.PlayerEntity;

import javax.annotation.Nullable;
import java.util.Objects;

public class GiveRune {

    public static void register(CommandDispatcher<CommandSource> commandDispatcher) {
        commandDispatcher.register(Commands.literal("giverune")
                .requires(e -> e.hasPermissionLevel(2))
                .then(Commands.argument("target", EntityArgument.player())
                        .then(Commands.argument("type", StringArgumentType.word())
                                .suggests(new RuneItemSuggestions())
                                .then(Commands.argument("level", IntegerArgumentType.integer())
                                        .then(Commands.argument("rarity", IntegerArgumentType
                                                .integer(0, 5))
                                                .then(Commands.argument("amount", IntegerArgumentType
                                                        .integer(1, 30000))

                                                        .executes(e -> run(e.getSource(), EntityArgument
                                                                .getPlayer(e, "target"), StringArgumentType
                                                                .getString(e, "type"), IntegerArgumentType
                                                                .getInteger(e, "level"), IntegerArgumentType
                                                                .getInteger(e, "rarity"), IntegerArgumentType
                                                                .getInteger(e, "amount")

                                                        ))))))));
    }

    private static int run(CommandSource commandSource, @Nullable PlayerEntity player,
                           String type, int lvl, int rarity, int amount) {

        if (Objects.isNull(player)) {
            try {
                player = commandSource.asPlayer();
            } catch (CommandSyntaxException e) {
                e.printStackTrace();
                return 1;
            }
        }

        for (int i = 0; i < amount; i++) {
            RuneBlueprint blueprint = new RuneBlueprint(lvl);
            if (rarity > -2) {
                blueprint.rarity.setSpecificRarity(rarity);
            }

            if (type.equals("random") || type.isEmpty()) {

            } else {
                blueprint.runePart.set(SlashRegistry.Runes().get(type));

            }

            blueprint.level.LevelRange = false;

            player.addItemStackToInventory(blueprint.createStack());
        }

        return 0;
    }
}
