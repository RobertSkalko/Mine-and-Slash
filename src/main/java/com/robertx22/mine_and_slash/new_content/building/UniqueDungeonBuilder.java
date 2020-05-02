package com.robertx22.mine_and_slash.new_content.building;

import com.robertx22.mine_and_slash.data_generation.unique_dungeons.UniqueDungeon;
import com.robertx22.mine_and_slash.data_generation.unique_dungeons.UniqueDungeonsReloadListener;
import com.robertx22.mine_and_slash.new_content.BuiltRoom;
import com.robertx22.mine_and_slash.new_content.RoomRotation;
import com.robertx22.mine_and_slash.new_content.RoomSides;
import com.robertx22.mine_and_slash.new_content.enums.RoomSide;
import com.robertx22.mine_and_slash.new_content.enums.RoomType;
import com.robertx22.mine_and_slash.new_content.registry.DungeonRoom;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.ChunkPos;

import java.util.Random;

public class UniqueDungeonBuilder {

    public UniqueDungeonBuilder(long worldSeed, ChunkPos cpos) {

        int chunkX = DungeonUtils.getStartChunk(cpos).x;
        int chunkZ = DungeonUtils.getStartChunk(cpos).z;

        long newSeed = (worldSeed + (long) (chunkX * chunkX * 4987142) + (long) (chunkX * 5947611) + (long) (chunkZ * chunkZ) * 4392871L + (long) (chunkZ * 389711) ^ worldSeed);
        rand = new Random(newSeed);

        this.uniqueDungeon = RandomUtils.weightedRandom(SlashRegistry.UniqueDungeons()
            .getList(), rand.nextDouble());

    }

    public int maxPuzzleBlockRooms = 3;

    public Dungeon dungeon;
    public final Random rand;
    public UniqueDungeon uniqueDungeon;

    public void build() {
        dungeon = new Dungeon(25);

        int mid = dungeon.getMiddle();

        UniqueDungeonsReloadListener.MAP.get(uniqueDungeon.GUID())
            .forEach(x -> {

                ChunkPos place = DungeonUtils.getPosFromFileName(x);

                RoomRotation rot = new RoomRotation(
                    RoomType.ENTRANCE,
                    new RoomSides(RoomSide.DOOR, RoomSide.DOOR, RoomSide.DOOR, RoomSide.DOOR),
                    Rotation.NONE);

                BuiltRoom built = new BuiltRoom(rot, new DungeonRoom(x));

                dungeon.addRoom(place.x + mid, place.z + mid, built);
            });

        dungeon.fillWithBarriers();

    }

}

