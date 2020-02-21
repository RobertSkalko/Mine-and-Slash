package com.robertx22.mine_and_slash.commands.misc;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.robertx22.mine_and_slash.commands.CommandRefs;
import com.robertx22.mine_and_slash.commands.suggestions.MapEventSuggestions;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.uncommon.capability.world.WorldMapCap;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.WorldUtils;
import com.robertx22.mine_and_slash.uncommon.wrappers.SText;
import net.minecraft.command.CommandSource;
import net.minecraft.world.World;

import static net.minecraft.command.Commands.argument;
import static net.minecraft.command.Commands.literal;

public class StartMapEvent {

    public static void register(CommandDispatcher<CommandSource> commandDispatcher) {
        commandDispatcher.register(

            literal(CommandRefs.ID)
                .requires(e -> e.hasPermissionLevel(2))
                .then(literal("start")
                    .then(literal("map")
                        .then(literal("event")
                            .requires(e -> e.hasPermissionLevel(2))
                            .then(argument("event", StringArgumentType.string())
                                .suggests(new MapEventSuggestions())
                                .executes(ctx -> run(
                                    ctx.getSource(),
                                    StringArgumentType.getString(ctx, "event")
                                )))))));
    }

    private static int run(CommandSource source, String id) {

        try {
            World world = source.getWorld();

            if (WorldUtils.isMapWorldClass(world)) {
                world.getCapability(WorldMapCap.Data)
                    .ifPresent(x -> x.startEvent(SlashRegistry.MapEvents()
                        .get(id), world));
            } else {
                source.getEntity()
                    .sendMessage(new SText("You can't summon map event outside map"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 1;
    }
}
