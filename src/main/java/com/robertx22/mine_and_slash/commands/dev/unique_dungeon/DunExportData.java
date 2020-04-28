package com.robertx22.mine_and_slash.commands.dev.unique_dungeon;

import com.robertx22.mine_and_slash.mmorpg.Ref;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.server.ServerWorld;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

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

    public int getStartY() {

        if (firstPos.getY() < lastPos.getY()) {
            return firstPos.getY();
        } else {
            return lastPos.getY();
        }

    }

    public void save(ChunkPos cp, PlayerEntity player, String name) {
        ResourceLocation res = new ResourceLocation(Ref.MODID, name);

        BlockPos first = new BlockPos(cp.getXStart(), getStartY(), cp.getZStart());
        BlockPos size = new BlockPos(16, 32, 16);

        ServerWorld world = (ServerWorld) player.world;
        TemplateManager manager = world.getStructureTemplateManager();

        Template template = manager.getTemplateDefaulted(res);

        template.takeBlocksFromWorld(player.world, first, size, false, Blocks.STRUCTURE_VOID);
        template.setAuthor(Ref.MODID);

        manager.writeToFile(res);

    }

    public void export(PlayerEntity player, String dungeonName) {
        List<ChunkPos> chunks = getAllChunks();

        chunks.forEach(x -> {

            ChunkPos norm = getNormalizedChunkPos(chunks, x);

            String name = "[" + norm.x + "-" + norm.z + "]";

            save(x, player, name);

        });

    }

    public ChunkPos getNormalizedChunkPos(List<ChunkPos> list, ChunkPos pos) {

        List<ChunkPos> normalized = new ArrayList<>();

        int smallestX = list.stream()
            .min(Comparator.comparingInt(c -> -c.x))
            .get().x;
        int smallestZ = list.stream()
            .min(Comparator.comparingInt(c -> -c.z))
            .get().z;

        return new ChunkPos(pos.x - smallestX, pos.z - smallestZ);

    }

    public List<ChunkPos> getAllChunks() {
        ChunkPos p1 = new ChunkPos(firstPos);
        ChunkPos p2 = new ChunkPos(lastPos);

        int x1 = p1.x < p2.x ? p1.x : p2.x;
        int z1 = p1.z < p2.z ? p1.z : p2.z;

        int x2 = p1.x > p2.x ? p1.x : p2.x;
        int z2 = p1.z > p2.z ? p1.z : p2.z;

        List<ChunkPos> list = new ArrayList<>();

        for (int x = x1; x < x2; x++) {
            for (int z = z1; z < z2; z++) {
                list.add(new ChunkPos(x, z));
            }
        }

        return list;
    }

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
