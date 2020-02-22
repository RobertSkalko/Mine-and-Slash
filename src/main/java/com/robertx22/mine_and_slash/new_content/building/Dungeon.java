package com.robertx22.mine_and_slash.new_content.building;

import com.robertx22.mine_and_slash.new_content.BuiltRoom;
import com.robertx22.mine_and_slash.new_content.RoomSides;
import com.robertx22.mine_and_slash.new_content.UnbuiltRoom;
import com.robertx22.mine_and_slash.new_content.enums.RoomSide;
import net.minecraft.util.Direction;
import net.minecraft.util.math.ChunkPos;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.ArrayList;
import java.util.List;

public class Dungeon {

    public Dungeon(int size) {
        this.size = size;
    }

    private BuiltRoom[][] rooms = new BuiltRoom[100][100];

    public List<ImmutablePair<Integer, Integer>> unbuiltRooms = new ArrayList<>();

    public boolean isFinished() {
        return started && unbuiltRooms.isEmpty();
    }

    public boolean shouldStartFinishing() {
        return amount > size;
    }

    private int size;
    private boolean started = false;

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

    public ImmutablePair<Integer, Integer> getCoordsOfRoomFacing(Direction dir, int x, int z) {
        if (dir == Direction.NORTH) {
            return ImmutablePair.of(x + 1, z);
        } else if (dir == Direction.SOUTH) {
            return ImmutablePair.of(x - 1, z);
        } else if (dir == Direction.EAST) {
            return ImmutablePair.of(x, z + 1);
        } else if (dir == Direction.WEST) {
            return ImmutablePair.of(x, z - 1);
        }
        return null;
    }

    public BuiltRoom getRoomFacing(Direction dir, int x, int z) {
        ImmutablePair<Integer, Integer> coords = getCoordsOfRoomFacing(dir, x, z);
        if (coords != null) {
            return getRoom(coords.left, coords.right);
        }
        return null;
    }

    public RoomSide getSideOfRoomFacing(Direction dir, int x, int z) {

        BuiltRoom room = getRoomFacing(dir, x, z);

        if (dir == Direction.NORTH) {
            return room != null ? room.data.sides.SOUTH : RoomSide.NONE;
        } else if (dir == Direction.SOUTH) {
            return room != null ? room.data.sides.NORTH : RoomSide.NONE;
        } else if (dir == Direction.EAST) {
            return room != null ? room.data.sides.WEST : RoomSide.NONE;
        } else if (dir == Direction.WEST) {
            return room != null ? room.data.sides.EAST : RoomSide.NONE;
        }

        System.out.println("No room found facing in direction of: " + dir.toString() + ": " + x + " , " + z);

        return null;
    }

    public UnbuiltRoom getUnbuiltFor(int x, int z) {

        RoomSide S = getSideOfRoomFacing(Direction.SOUTH, x, z);
        RoomSide N = getSideOfRoomFacing(Direction.NORTH, x, z);
        RoomSide E = getSideOfRoomFacing(Direction.EAST, x, z);
        RoomSide W = getSideOfRoomFacing(Direction.WEST, x, z);

        RoomSides sides = new RoomSides(S, N, E, W);

        UnbuiltRoom unbuilt = new UnbuiltRoom(sides);

        return unbuilt;
    }

    private void addUnbuilts(int x, int z, BuiltRoom room) {

        List<Direction> dirs = new ArrayList<>();
        dirs.add(Direction.SOUTH);
        dirs.add(Direction.NORTH);
        dirs.add(Direction.WEST);
        dirs.add(Direction.EAST);

        RoomSides sides = room.data.sides;

        dirs.forEach(dir -> {
            ImmutablePair<Integer, Integer> coord = getCoordsOfRoomFacing(dir, x, z);
            if (rooms[coord.left][coord.right] == null) {
                if (room.data.sides.getSideOfDirection(dir) == RoomSide.DOOR) {
                    this.unbuiltRooms.add(coord);
                }
            }
        });
    }

    public void addRoom(int x, int z, BuiltRoom room) {
        if (rooms[x][z] == null) {
            rooms[x][z] = room;
            amount++;

            this.started = true;

            addUnbuilts(x, z, room);

            unbuiltRooms.removeIf(cord -> {
                return cord.left == x && cord.right == z;
            });

        } else {
            System.out.println("Error, setting room that already exists!");
        }
    }

}
