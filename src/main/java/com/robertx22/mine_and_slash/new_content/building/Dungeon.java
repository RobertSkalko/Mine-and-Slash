package com.robertx22.mine_and_slash.new_content.building;

import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.new_content.BuiltRoom;
import com.robertx22.mine_and_slash.new_content.RoomRotation;
import com.robertx22.mine_and_slash.new_content.RoomSides;
import com.robertx22.mine_and_slash.new_content.UnbuiltRoom;
import com.robertx22.mine_and_slash.new_content.enums.RoomGroup;
import com.robertx22.mine_and_slash.new_content.enums.RoomSide;
import com.robertx22.mine_and_slash.new_content.enums.RoomType;
import com.robertx22.mine_and_slash.new_content.registry.DungeonRoom;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.ChunkPos;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Dungeon {

    public Dungeon(int size) {
        this.size = size;
        this.capacity = size / 2;

        rooms = new BuiltRoom[capacity][capacity];

    }

    int capacity;
    private int size;
    private boolean started = false;
    int amount = 0;
    int ends = 0;
    public int bossRooms = 0;

    private BuiltRoom[][] rooms;

    private List<ImmutablePair<Integer, Integer>> unbuiltRooms = new ArrayList<>();

    public List<ImmutablePair<Integer, Integer>> getUnbuiltCopy() {
        return new ArrayList<>(unbuiltRooms);
    }

    public void printDungeonAsSymbolsForDebug() {

        String all = "";

        for (int x = 0; x < capacity; x++) {

            String line = "";

            for (int z = 0; z < capacity; z++) {
                if (getRoom(x, z) != null) {

                    line += getRoom(x, z).data.type.id + ";";

                } else {
                    line += "empty;";
                }
            }

            all += line + "\n";
        }

        System.out.println(all);

    }

    public boolean isFinished() {
        return (started && unbuiltRooms.isEmpty());
    }

    public boolean shouldStartFinishing() {
        return amount > size;
    }

    public BuiltRoom getRoomForChunk(ChunkPos pos) {
        try {
            ChunkPos start = DungeonUtils.getStartChunk(pos);
            ChunkPos relative = new ChunkPos(pos.x - start.x, pos.z - start.z);
            return rooms[getMiddle() + relative.x][getMiddle() + relative.z];
        } catch (Exception e) {
        }
        return null;

    }

    public boolean hasRoomForChunk(ChunkPos pos) {
        return getRoomForChunk(pos) != null;
    }

    public BuiltRoom getRoom(int x, int z) {

        if (x > capacity || z > capacity || x < 0 || z < 0) {
            return null;
        }

        return rooms[x][z];
    }

    public int getMiddle() {
        return rooms.length / 2;
    }

    public ImmutablePair<Integer, Integer> getCoordsOfRoomFacing(Direction dir, int x, int z) {
        if (dir == Direction.NORTH) {
            return ImmutablePair.of(x, z - 1);
        } else if (dir == Direction.SOUTH) {
            return ImmutablePair.of(x, z + 1);
        } else if (dir == Direction.EAST) {
            return ImmutablePair.of(x + 1, z);
        } else if (dir == Direction.WEST) {
            return ImmutablePair.of(x - 1, z);
        }

        throw new RuntimeException("getCoordsOfRoomFacing is null? Wrong direction?");
    }

    public void setupBarriers() {
        DungeonRoom barrier = new DungeonRoom("", RoomType.END, RoomGroup.TEST);
        barrier.loc = new ResourceLocation(Ref.MODID, "dun/barrier");
        RoomRotation rot = new RoomRotation(RoomType.END, new RoomSides(RoomSide.BLOCKED, RoomSide.BLOCKED, RoomSide.BLOCKED, RoomSide.BLOCKED), Rotation.NONE);
        BuiltRoom built = new BuiltRoom(rot, barrier);

        // add barriers to edges
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[i].length; j++) {
                if (i == 0 || j == 0 || i == rooms.length - 1 || j == rooms[i].length - 1) {
                    addBarrier(i, j, built);
                }
            }
        }
    }

    public BuiltRoom getRoomFacing(Direction dir, int x, int z) {
        ImmutablePair<Integer, Integer> coords = getCoordsOfRoomFacing(dir, x, z);
        if (coords != null) {
            return getRoom(coords.left, coords.right);
        }

        throw new RuntimeException("getRoomFacing is null? Wrong direction?");
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

        throw new RuntimeException("No room found facing in direction of: " + dir.toString() + ": " + x + " , " + z);

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

        /*
        if (x > capacity * 0.95F || z > capacity * 0.95F) {
            System.out.println("Pushing too close to capacity, not adding any unbuilt rooms. This means it will look broken.");
            return;
        }

         */

        List<Direction> dirs = new ArrayList<>();
        dirs.add(Direction.SOUTH);
        dirs.add(Direction.NORTH);
        dirs.add(Direction.WEST);
        dirs.add(Direction.EAST);

        dirs.forEach(dir -> {
            ImmutablePair<Integer, Integer> coord = getCoordsOfRoomFacing(dir, x, z);
            if (getRoom(coord.left, coord.right) == null) {
                if (room.data.sides.getSideOfDirection(dir) == RoomSide.DOOR) {
                    this.unbuiltRooms.add(coord);
                }
            }
        });
    }

    public void addBarrier(int x, int z, BuiltRoom room) {
        rooms[x][z] = room;
    }

    public void addRoom(int x, int z, BuiltRoom room) {

        if (room == null) {
            return;
        }

        if (rooms[x][z] == null) {
            rooms[x][z] = room;
            amount++;

            if (room.data.type.equals(RoomType.END)) {
                ends++;
            }
            if (room.room.isBoss) {
                bossRooms++;
            }

            this.started = true;

            addUnbuilts(x, z, room);

            unbuiltRooms.removeIf(cord -> {
                return cord.left == x && cord.right == z;
            });

        } else {
            //System.out.println("Error, setting room that already exists!");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dungeon d = (Dungeon) o;

        return d.amount == amount && d.ends == ends;

    }

    @Override
    public int hashCode() {
        return Objects.hash(this);
    }
}
