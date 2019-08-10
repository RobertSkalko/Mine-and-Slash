package com.robertx22.mine_and_slash.commands.entity;

import com.mojang.brigadier.CommandDispatcher;
import com.robertx22.mine_and_slash.uncommon.capability.PlayerMapCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.datasaving.Map;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.StringTextComponent;

import javax.annotation.Nullable;

public class SayMapInfo {

    public static void register(CommandDispatcher<CommandSource> commandDispatcher) {
        commandDispatcher.register(Commands.literal("givemapinfo")
                .requires(e -> e.hasPermissionLevel(0))
                .then(Commands.argument("target", EntityArgument.entity())

                        .executes(ctx -> run(ctx.getSource(), EntityArgument.getPlayer(ctx, "target")))));
    }

    private static int run(CommandSource source, @Nullable Entity en) {

        try {

            if (en instanceof PlayerEntity) {

                PlayerMapCap.IPlayerMapData data = Load.playerMapData((PlayerEntity) en);

                CompoundNBT nbt = new CompoundNBT();
                Map.Save(nbt, data.getMap());

                if (source.asPlayer() != null) {
                    source.asPlayer()
                            .sendMessage(new StringTextComponent("Player : ").appendSibling(en
                                    .getName())
                                    .appendText(" map info is: " + nbt.toString()));

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 1;
    }
}
