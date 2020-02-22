package com.robertx22.mine_and_slash.new_content.building;

import com.robertx22.mine_and_slash.new_content.BuiltRoom;
import com.robertx22.mine_and_slash.new_content.RoomRotation;
import com.robertx22.mine_and_slash.new_content.enums.RoomType;
import com.robertx22.mine_and_slash.new_content.registry.DungeonRoom;
import com.robertx22.mine_and_slash.new_content.registry.RoomsList;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.ChunkPos;

import java.util.Random;

public class DungeonBuilder {

    public DungeonBuilder(long worldSeed, ChunkPos cpos) {

        int chunkX = cpos.x;
        int chunkZ = cpos.z;
        int distToEntranceX = 8 - (chunkX % 16);
        int distToEntranceZ = 11 - (chunkZ % 16);
        chunkX += distToEntranceX;
        chunkZ += distToEntranceZ;

        long newSeed = (worldSeed + (long) (chunkX * chunkX * 4987142) + (long) (chunkX * 5947611) + (long) (chunkZ * chunkZ) * 4392871L + (long) (chunkZ * 389711) ^ worldSeed);
        rand = new Random(newSeed);

    }

    Dungeon dungeon;
    Random rand;
    public int size = 25;
    public boolean isTesting = false;

    public void build() {
        dungeon = new Dungeon();

        setupEntrance();
    }

    private void setupEntrance() {
        DungeonRoom entranceRoom = RoomsList.randomDungeonRoom(RoomsList.getAllOfType(RoomType.ENTRANCE), rand);
        RoomRotation rotation = new RoomRotation(RoomType.ENTRANCE, RoomType.ENTRANCE.sides, Rotation.NONE);
        BuiltRoom entrance = new BuiltRoom(rotation, entranceRoom.loc);

        int mid = dungeon.getMiddle();
        dungeon.setRoomFor(mid, mid, entrance);
    }

}
