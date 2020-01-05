package com.robertx22.mine_and_slash.commands.giveitems;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.robertx22.mine_and_slash.commands.bases.CrateSuggestions;
import com.robertx22.mine_and_slash.database.loot_crates.LootCrate;
import com.robertx22.mine_and_slash.database.loot_crates.loot_crate_item.MapLootCrateItem;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.ITiered;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;
import java.util.Objects;

public class GiveCrate {

    public static void register(CommandDispatcher<CommandSource> commandDispatcher) {
        commandDispatcher.register(Commands.literal("givelootcrate")
                .requires(e -> e.hasPermissionLevel(2))
                .then(Commands.argument("target", EntityArgument.player())
                        .then(Commands.argument("type", StringArgumentType.word())
                                .suggests(new CrateSuggestions())
                                .then(Commands.argument("level", IntegerArgumentType.integer())
                                        .then(Commands.argument("score", IntegerArgumentType
                                                .integer(1, 5))
                                                .then(Commands.argument("amount", IntegerArgumentType
                                                        .integer(1, 5000))
                                                        .then(Commands.argument("tier", IntegerArgumentType
                                                                .integer(0, ITiered.MAX_TIER))
                                                                .executes(e -> execute(e.getSource(), EntityArgument
                                                                        .getPlayer(e, "target"), StringArgumentType
                                                                        .getString(e, "type"), IntegerArgumentType
                                                                        .getInteger(e, "level"), IntegerArgumentType
                                                                        .getInteger(e, "score"), IntegerArgumentType
                                                                        .getInteger(e, "amount"), IntegerArgumentType
                                                                        .getInteger(e, "tier")

                                                                )))))))));
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

        LootCrate crate = SlashRegistry.LootCrates().get(type);

        ItemStack stack = MapLootCrateItem.getStack(crate, lvl, tier, score);

        for (int i = 0; i < amount; i++) {
            player.addItemStackToInventory(stack);
        }

        return 0;
    }
}
