package com.robertx22.mine_and_slash.commands.giveitems;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.robertx22.mine_and_slash.commands.CommandRefs;
import com.robertx22.mine_and_slash.commands.suggestions.WorldTypesSuggestions;
import com.robertx22.mine_and_slash.loot.blueprints.MapBlueprint;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.PlayerEntity;

import javax.annotation.Nullable;
import java.util.Objects;

import static net.minecraft.command.Commands.literal;

public class GiveMap {

    public static void register(CommandDispatcher<CommandSource> commandDispatcher) {
        commandDispatcher.register(
            literal(CommandRefs.ID)
                .requires(e -> e.hasPermissionLevel(2))
                .then(literal("give")
                    .then(literal("map")
                        .then(Commands.argument("target", EntityArgument.player())
                            .then(Commands.argument("level", IntegerArgumentType.integer())
                                .then(Commands.argument("world_type", StringArgumentType.string())
                                    .suggests(new WorldTypesSuggestions())
                                    .then(Commands.argument("rarity", IntegerArgumentType.integer(0, 5))
                                        .then(Commands.argument("tier", IntegerArgumentType
                                            .integer(0, 30))
                                            .then(Commands.argument("amount", IntegerArgumentType
                                                .integer(1, 30000))
                                                .executes(e -> run(e.getSource(),
                                                    EntityArgument.getPlayer(e, "target"),
                                                    IntegerArgumentType.getInteger(e, "level"),
                                                    StringArgumentType.getString(e, "world_type"),
                                                    IntegerArgumentType.getInteger(e, "rarity"),
                                                    IntegerArgumentType.getInteger(e, "tier"),
                                                    IntegerArgumentType.getInteger(e, "amount")
                                                )))))))))));
    }

    private static int run(CommandSource commandSource, @Nullable PlayerEntity player,
                           int lvl, String worldtype, int rarity, int tier, int amount) {

        if (Objects.isNull(player)) {
            try {
                player = commandSource.asPlayer();
            } catch (CommandSyntaxException e) {
                e.printStackTrace();
                return 1;
            }
        }
        for (int i = 0; i < amount; i++) {

            MapBlueprint blueprint = new MapBlueprint(lvl, tier);
            if (rarity > -1) {
                blueprint.rarity.setSpecificRarity(rarity);
            }

            if (SlashRegistry.WorldProviders()
                .isRegistered(worldtype)) {
                blueprint.iwp.set(SlashRegistry.WorldProviders()
                    .get(worldtype));
            }

            blueprint.level.LevelRange = false;

            player.addItemStackToInventory(blueprint.createStack());
        }

        return 0;
    }
}
