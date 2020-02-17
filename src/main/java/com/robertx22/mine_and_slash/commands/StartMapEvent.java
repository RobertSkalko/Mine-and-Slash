package com.robertx22.mine_and_slash.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.robertx22.mine_and_slash.commands.bases.MapEventSuggestions;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.uncommon.capability.WorldMapCap;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.WorldUtils;
import com.robertx22.mine_and_slash.uncommon.wrappers.SText;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.world.World;

public class StartMapEvent {

    public static void register(CommandDispatcher<CommandSource> commandDispatcher) {
        commandDispatcher.register(Commands.literal("startmapevent")
                                           .requires(e -> e.hasPermissionLevel(2))
                                           .then(Commands.argument("event", StringArgumentType.string())
                                                         .suggests(new MapEventSuggestions())
                                                         .executes(ctx -> run(
                                                                 ctx.getSource(),
                                                                 StringArgumentType.getString(ctx, "event")
                                                         ))));
    }

    private static int run(CommandSource source, String id) {

        try {
            World world = source.getWorld();

            if (WorldUtils.isMapWorldClass(world)) {
                world.getCapability(WorldMapCap.Data)
                        .ifPresent(x -> x.startEvent(SlashRegistry.MapEvents().get(id), world));
            } else {
                source.getEntity().sendMessage(new SText("You can't summon map event outside map"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 1;
    }
}
