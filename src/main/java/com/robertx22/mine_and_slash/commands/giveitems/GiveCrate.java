package com.robertx22.mine_and_slash.commands.giveitems;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.robertx22.mine_and_slash.commands.CommandRefs;
import com.robertx22.mine_and_slash.commands.suggestions.CrateSuggestions;
import com.robertx22.mine_and_slash.database.loot_crates.bases.LootCrate;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.ITiered;
import net.minecraft.command.CommandSource;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;
import java.util.Objects;

import static net.minecraft.command.Commands.argument;
import static net.minecraft.command.Commands.literal;

public class GiveCrate {

    public static void register(CommandDispatcher<CommandSource> commandDispatcher) {
        commandDispatcher.register(
            literal(CommandRefs.ID)
                .then(literal("give").requires(e -> e.hasPermissionLevel(2))
                    .then(literal("loot_crate")
                        .requires(e -> e.hasPermissionLevel(2))
                        .then(argument("target", EntityArgument.player())
                            .then(argument("type", StringArgumentType.word())
                                .suggests(new CrateSuggestions())
                                .then(argument("level", IntegerArgumentType.integer())
                                    .then(argument("score", IntegerArgumentType
                                        .integer(1, 5))
                                        .then(argument("amount", IntegerArgumentType
                                            .integer(1, 5000))
                                            .then(argument("tier", IntegerArgumentType
                                                .integer(0, ITiered.getMaxTier()))
                                                .executes(e -> execute(e.getSource(), EntityArgument
                                                    .getPlayer(e, "target"), StringArgumentType
                                                    .getString(e, "type"), IntegerArgumentType
                                                    .getInteger(e, "level"), IntegerArgumentType
                                                    .getInteger(e, "score"), IntegerArgumentType
                                                    .getInteger(e, "amount"), IntegerArgumentType
                                                    .getInteger(e, "tier")

                                                )))))))))));
    }

    private static int execute(CommandSource commandSource, @Nullable PlayerEntity player,
                               String type, int lvl, int score, int amount, int tier) {

        if (Objects.isNull(player)) {
            try {
                player = commandSource.asPlayer();
            } catch (CommandSyntaxException e) {
                e.printStackTrace();
                return 1;
            }
        }

        LootCrate crate = null;
        if (SlashRegistry.LootCrates()
            .isRegistered(type)) {
            crate = SlashRegistry.LootCrates()
                .get(type);
        } else {
            crate = SlashRegistry.LootCrates()
                .random();
        }

        ItemStack stack = crate.getCrateStack(lvl, tier, score);

        for (int i = 0; i < amount; i++) {
            player.addItemStackToInventory(stack);
        }

        return 0;
    }
}
