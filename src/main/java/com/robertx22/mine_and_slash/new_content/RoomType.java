package com.robertx22.mine_and_slash.new_content;

import net.minecraft.util.Rotation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public enum RoomType {

    FOUR_WAY(RoomSide.DOOR, RoomSide.DOOR, RoomSide.DOOR, RoomSide.DOOR, "four_way"),
    STRAIGHT_HALLWAY(RoomSide.DOOR, RoomSide.DOOR, RoomSide.BLOCKED, RoomSide.BLOCKED, "straight_hallway"),
    CURVED_HALLWAY(RoomSide.DOOR, RoomSide.BLOCKED, RoomSide.DOOR, RoomSide.BLOCKED, "curved_hallway"),
    TRIPLE_HALLWAY(RoomSide.BLOCKED, RoomSide.DOOR, RoomSide.DOOR, RoomSide.DOOR, "triple_hallway"),
    END(RoomSide.DOOR, RoomSide.BLOCKED, RoomSide.BLOCKED, RoomSide.BLOCKED, "end"),
    ENTRANCE(RoomSide.DOOR, RoomSide.BLOCKED, RoomSide.DOOR, RoomSide.DOOR, "entrance");

    RoomSidesData sides;
    List<RoomData> rotations;
    public String id;

    RoomType(RoomSide SOUTH, RoomSide NORTH, RoomSide EAST, RoomSide WEST, String id) {
        this.sides = new RoomSidesData(SOUTH, NORTH, EAST, WEST);
        this.id = id;

        this.rotations = new ArrayList<>();

        RoomSidesData current = new RoomSidesData(SOUTH, NORTH, EAST, WEST);

        Rotation rotation = Rotation.NONE;

        rotations.add(new RoomData(this, current, rotation));

        for (int i = 0; i < 3; i++) {
            rotation = rotation.add(Rotation.CLOCKWISE_90);
            current = new RoomSidesData(WEST, EAST, SOUTH, NORTH);
            rotations.add(new RoomData(this, current, rotation));
        }
    }

    public List<RoomData> getPossibleFor(UnbuiltRoom room) {
        return rotations.stream()
            .filter(x -> x.sides.matches(room.sides))
            .collect(Collectors.toList());
    }
}

// XOX
// OOO
// XOX