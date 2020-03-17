package com.robertx22.mine_and_slash.commands.dev;

import com.mojang.brigadier.CommandDispatcher;
import com.robertx22.mine_and_slash.commands.CommandRefs;
import com.robertx22.mine_and_slash.dimensions.MapManager;
import com.robertx22.mine_and_slash.new_content.building.DungeonUtils;
import com.robertx22.mine_and_slash.saveclasses.dungeon_dimension.DungeonDimensionData;
import com.robertx22.mine_and_slash.saveclasses.item_classes.MapItemData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.EntityUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.PlayerUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.WorldUtils;
import net.minecraft.command.CommandSource;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Vec3d;

import javax.annotation.Nullable;

import static net.minecraft.command.Commands.argument;
import static net.minecraft.command.Commands.literal;

public class TpRandomDungeon {
    public static void register(CommandDispatcher<CommandSource> commandDispatcher) {
        commandDispatcher.register(
            literal(CommandRefs.ID)
                .then(literal("dev").requires(e -> e.hasPermissionLevel(2))
                    .then(literal("tp")
                        .requires(e -> e.hasPermissionLevel(2))
                        .then(literal("random_dungeon")
                            .then(argument("target", EntityArgument.player())
                                .executes(ctx -> run(EntityArgument.getPlayer(ctx, "target"))))))));
    }

    private static int run(@Nullable PlayerEntity player) {

        MapItemData map = new MapItemData();

        ChunkPos cpos = Load.world(MapManager.getWorld(MapManager.getDungeonDimensionType()))
            .getData()
            .randomFree();

        BlockPos pos = DungeonUtils.getDungeonStartTeleportPos(cpos);

        String dungeonID = DungeonDimensionData.getId(cpos);

        Load.world(MapManager.getWorld(MapManager.getDungeonDimensionType()))
            .init(map, cpos);

        Load.world(player.world)
            .init(map, cpos);

        if (WorldUtils.isMapWorldClass(player.world)) {
            EntityUtils.setLoc(player, new Vec3d(pos), 0, 0);
        } else {
            PlayerUtils.changeDimension((ServerPlayerEntity) player, MapManager.getDungeonDimensionType(), pos);
        }

        return 1;
    }
}
