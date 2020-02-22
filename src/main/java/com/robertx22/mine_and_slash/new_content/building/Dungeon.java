package com.robertx22.mine_and_slash.new_content.building;

import com.robertx22.mine_and_slash.new_content.BuiltRoom;
import com.robertx22.mine_and_slash.new_content.RoomSides;
import com.robertx22.mine_and_slash.new_content.UnbuiltRoom;
import com.robertx22.mine_and_slash.new_content.enums.RoomSide;
import net.minecraft.util.math.ChunkPos;

public class Dungeon {

    private BuiltRoom[][] rooms = new BuiltRoom[100][100];

    public BuiltRoom getRoomForChunk(ChunkPos pos) {
        return rooms[pos.x][pos.z];
    }

    public BuiltRoom getRoom(int x, int z) {
        return rooms[x][z];
    }

    public int getMiddle() {
        return rooms.length / 2;
    }

    int amount = 0;

    public UnbuiltRoom getUnbuiltFor(int x, int z) {

        BuiltRoom southRoom = getRoom(x - 1, z);
        BuiltRoom northRoom = getRoom(x + 1, z);
        BuiltRoom eastRoom = getRoom(x, z - 1);
        BuiltRoom westRoom = getRoom(x, z + 1);

        RoomSide south =

            RoomSides sides = new RoomSides();

        UnbuiltRoom unbuilt = new UnbuiltRoom();

        return unbuilt;
    }

    public void setRoomFor(int x, int z, BuiltRoom room) {
        if (rooms[x][z] == null) {
            rooms[x][z] = room;
            amount++;
        } else {
            System.out.println("Error, setting room that already exists!");
        }
    }

}
