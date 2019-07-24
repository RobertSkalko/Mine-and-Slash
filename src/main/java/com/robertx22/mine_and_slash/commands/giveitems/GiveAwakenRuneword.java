package com.robertx22.mine_and_slash.commands.giveitems;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.robertx22.mine_and_slash.commands.bases.GearTypeSuggestions;
import com.robertx22.mine_and_slash.loot.blueprints.AwakenRuneWordBlueprint;
import com.robertx22.mine_and_slash.loot.gens.AwakenRuneWordLootGen;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.PlayerEntity;

import javax.annotation.Nullable;
import java.util.Objects;

public class GiveAwakenRuneword {

    public static void register(CommandDispatcher<CommandSource> commandDispatcher) {
        commandDispatcher.register(Commands.literal("giveawakenruneword")
                .requires(e -> e.hasPermissionLevel(2))
                .then(Commands.argument("target", EntityArgument.player())
                        .then(Commands.argument("type", StringArgumentType.word())
                                .suggests(new GearTypeSuggestions())
                                .then(Commands.argument("amount", IntegerArgumentType.integer(1, 30000))
                                        .executes(e -> run(e.getSource(), EntityArgument.getPlayer(e, "target"), StringArgumentType
                                                .getString(e, "type"), IntegerArgumentType
                                                .getInteger(e, "amount")

                                        ))))));
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
                blueprint.word = word;
            }

            try {
                player.addItemStackToInventory(AwakenRuneWordLootGen.Create(blueprint));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return 0;
    }
}
