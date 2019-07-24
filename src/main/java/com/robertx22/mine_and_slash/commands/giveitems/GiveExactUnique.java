package com.robertx22.mine_and_slash.commands.giveitems;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.robertx22.mine_and_slash.commands.bases.UniqueItemsSuggestions;
import com.robertx22.mine_and_slash.database.rarities.items.UniqueItem;
import com.robertx22.mine_and_slash.loot.blueprints.UniqueGearBlueprint;
import com.robertx22.mine_and_slash.loot.gens.UniqueGearLootGen;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.PlayerEntity;

import javax.annotation.Nullable;
import java.util.Objects;

public class GiveExactUnique {
    public static void register(CommandDispatcher<CommandSource> commandDispatcher) {
        commandDispatcher.register(Commands.literal("giveexactunique")
                .requires(e -> e.hasPermissionLevel(2))
                .then(Commands.argument("target", EntityArgument.player())
                        .then(Commands.argument("uniqueID", StringArgumentType.word())
                                .suggests(new UniqueItemsSuggestions())
                                .then(Commands.argument("level", IntegerArgumentType.integer())
                                        .then(Commands.argument("amount", IntegerArgumentType
                                                .integer(1, 5000))
                                                .executes(e -> execute(e.getSource(), EntityArgument
                                                        .getPlayer(e, "target"), StringArgumentType
                                                        .getString(e, "uniqueID"), IntegerArgumentType
                                                        .getInteger(e, "level"), IntegerArgumentType
                                                        .getInteger(e, "amount")

                                                )))))));
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

        UniqueGearBlueprint blueprint = new UniqueGearBlueprint(lvl, 0, true);
        blueprint.setSpecificRarity(new UniqueItem().Rank());
        blueprint.setSpecificID(id);
        blueprint.LevelRange = false;

        for (int i = 0; i < amount; i++) {
            player.addItemStackToInventory(UniqueGearLootGen.CreateStack(blueprint));
        }

        return 0;
    }
}
