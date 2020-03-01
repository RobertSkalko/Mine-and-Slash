package com.robertx22.mine_and_slash.commands.giveitems;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.robertx22.mine_and_slash.commands.CommandRefs;
import com.robertx22.mine_and_slash.commands.suggestions.GearTypeSuggestions;
import com.robertx22.mine_and_slash.loot.blueprints.UniqueGearBlueprint;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.PlayerEntity;

import javax.annotation.Nullable;
import java.util.Objects;

import static net.minecraft.command.Commands.literal;

public class GiveUniqueGear {

    public static void register(CommandDispatcher<CommandSource> commandDispatcher) {
        commandDispatcher.register(

            literal(CommandRefs.ID)
                .then(literal("give").requires(e -> e.hasPermissionLevel(2))
                    .then(literal("unique_gear")
                        .then(Commands.argument("target", EntityArgument.player())
                            .then(Commands.argument("type", StringArgumentType.word())
                                .suggests(new GearTypeSuggestions())
                                .then(Commands.argument("level",
                                    IntegerArgumentType.integer()
                                )
                                    .then(Commands.argument("tier",
                                        IntegerArgumentType
                                            .integer(
                                                0,
                                                30
                                            )
                                    )
                                        .then(Commands.argument(
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
                                                            "tier"
                                                        ),
                                                    IntegerArgumentType
                                                        .getInteger(
                                                            e,
                                                            "amount"
                                                        )

                                                ))))))))));
    }

    private static int execute(CommandSource commandSource, @Nullable PlayerEntity player, String type, int lvl,
                               int tier, int amount) {

        if (Objects.isNull(player)) {
            try {
                player = commandSource.asPlayer();
            } catch (CommandSyntaxException e) {
                e.printStackTrace();
                return 1;
            }
        }

        for (int i = 0; i < amount; i++) {

            UniqueGearBlueprint blueprint = new UniqueGearBlueprint(lvl, tier, true);

            if (type.equals("random") == false) {
                blueprint.gearItemSlot.set(type);
            }

            blueprint.level.LevelRange = false;

            player.addItemStackToInventory(blueprint.createStack());
        }

        return 0;
    }
}
