package com.robertx22.mine_and_slash.commands.dev.unique_dungeon;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;

import java.util.HashMap;

public class DunExportData {

    public static HashMap<PlayerEntity, DunExportData> MAP = new HashMap<>();

    public static void init(PlayerEntity player) {
        if (!MAP.containsKey(player)) {
            MAP.put(player, new DunExportData());
        }
    }

    public enum PosType {
        ENTRANCE, FIRST, LAST
    }

    BlockPos entrancePos;

    BlockPos firstPos;
    BlockPos lastPos;

    public void set(PosType type, BlockPos pos) {
        if (type == PosType.ENTRANCE) {
            this.entrancePos = pos;
        }
        if (type == PosType.FIRST) {
            this.firstPos = pos;
        }
        if (type == PosType.LAST) {
            this.lastPos = pos;
        }

    }

}
