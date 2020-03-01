package com.robertx22.mine_and_slash.commands.giveitems;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.robertx22.mine_and_slash.commands.CommandRefs;
import com.robertx22.mine_and_slash.commands.suggestions.GearTypeSuggestions;
import com.robertx22.mine_and_slash.loot.blueprints.GearBlueprint;
import com.robertx22.mine_and_slash.loot.gens.util.GearCreationUtils;
import net.minecraft.command.CommandSource;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;
import java.util.Objects;

import static net.minecraft.command.Commands.argument;
import static net.minecraft.command.Commands.literal;

public class GiveGear {

    public static void register(CommandDispatcher<CommandSource> commandDispatcher) {

        commandDispatcher.register(
            literal(CommandRefs.ID)
                .then(literal("give").requires(e -> e.hasPermissionLevel(2))
                    .then(literal("gear")
                        .then(argument("target", EntityArgument.player())
                            .then(argument("type", StringArgumentType.word())
                                .suggests(new GearTypeSuggestions())
                                .then(argument("level",
                                    IntegerArgumentType.integer()
                                )
                                    .then(argument(
                                        "rarity",
                                        IntegerArgumentType.integer(
                                            0, 5)
                                    )
                                        .then(argument(
                                            "amount",
                                            IntegerArgumentType
                                                .integer(
                                                    1,
                                                    5000
                                                )
                                        )
                                            .executes(
                                                e -> execute(
                                                    e.getSource(),
                                                    EntityArgument
                                                        .getPlayer(
                                                            e,
                                                            "target"
                                                        ),
                                                    StringArgumentType
                                                        .getString(
                                                            e,
                                                            "type"
                                                        ),
                                                    IntegerArgumentType
                                                        .getInteger(
                                                            e,
                                                            "level"
                                                        ),
                                                    IntegerArgumentType
                                                        .getInteger(
                                                            e,
                                                            "rarity"
                                                        ),
                                                    IntegerArgumentType
                                                        .getInteger(
                                                            e,
                                                            "amount"
                                                        )

                                                ))))))))));
    }

    private static int execute(CommandSource commandSource, @Nullable PlayerEntity player, String type, int lvl,
                               int rarity, int amount) {

        if (Objects.isNull(player)) {
            try {
                player = commandSource.asPlayer();
            } catch (CommandSyntaxException e) {
                e.printStackTrace();
                return 1;
            }
        }
        for (int i = 0; i < amount; i++) {

            GearBlueprint blueprint = new GearBlueprint(lvl);
            if (rarity > -1) {
                blueprint.rarity.setSpecificRarity(rarity);
            }
            if (!type.equals("random")) {
                blueprint.gearItemSlot.set(type);
            }
            blueprint.level.LevelRange = false;

            ItemStack stack = GearCreationUtils.CreateStack(blueprint);
            player.addItemStackToInventory(stack);
        }

        return 0;
    }
}
