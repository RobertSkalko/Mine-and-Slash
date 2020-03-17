package com.robertx22.mine_and_slash.saveclasses.dungeon_dimension;

import com.robertx22.mine_and_slash.dimensions.MapManager;
import com.robertx22.mine_and_slash.new_content.building.DungeonUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.border.WorldBorder;

import java.util.HashMap;

@Storable
public class DungeonDimensionData {

    @Store
    private HashMap<String, DungeonData> map = new HashMap<String, DungeonData>();

    public int getDungeonsAmount() {
        return map.size();
    }

    public DungeonData getData(BlockPos pos) {
        return map.get(getId(pos));
    }

    public DungeonData getData(ChunkPos pos) {
        return map.get(getId(pos));
    }

    public boolean hasData(BlockPos pos) {
        String id = getId(pos);
        return hasData(id);
    }

    public boolean hasData(ChunkPos pos) {
        String id = getId(pos);
        return hasData(id);
    }

    public boolean hasData(String id) {
        return map.containsKey(id) && map.get(id) != null;
    }

    public ChunkPos randomFree() {

        String id = "";
        ChunkPos pos = null;

        int tries = 0;

        WorldBorder border = MapManager.getWorld(MapManager.getDungeonDimensionType())
            .getWorldBorder();

        int max = border.getSize() / 16 / 2;

        while (id.isEmpty() || hasData(id)) {

            if (tries > 2500) {
                System.out.println("Tried too many times to find random dungeon pos and failed, please delete the map dimension folder");
                return null;
            }

            int x = RandomUtils.RandomRange(50, max);
            int z = RandomUtils.RandomRange(50, max);

            pos = new ChunkPos(x, z);

            pos = DungeonUtils.getStartChunk(pos);

            id = getId(pos);

        }

        if (tries > 1000) {
            System.out.println("It took more than 1000 tries to find random free dungeon, either you are insanely unlucky, or the world is close to filled! Dungeon worlds are cleared on next server boot if they reach too close to capacity.");
        }

        return pos;

    }

    public static String getId(BlockPos pos) {
        return getId(DungeonUtils.getStartChunk(new ChunkPos(pos)));
    }

    public static String getId(ChunkPos cpos) {
        cpos = DungeonUtils.getStartChunk(cpos);
        return cpos.x + "_" + cpos.z;
    }

    public static ChunkPos getChunkFromId(String id) {

        try {
            String[] parts = id.split("_");

            int x = Integer.parseInt(parts[0]);
            int z = Integer.parseInt(parts[1]);

            return new ChunkPos(x, z);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void setupNew(DungeonData d, ChunkPos pos) {
        if (hasData(pos)) {
            //throw new RuntimeException("Can't setup new dungeon over existing one!");
        } else {
            map.put(getId(pos), d);
        }
    }
}
