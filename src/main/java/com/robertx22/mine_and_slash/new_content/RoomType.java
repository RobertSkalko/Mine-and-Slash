package com.robertx22.mine_and_slash.new_content;

import net.minecraft.util.Rotation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public enum RoomType {

    FOUR_WAY(RoomSide.DOOR, RoomSide.DOOR, RoomSide.DOOR, RoomSide.DOOR),
    STRAIGHT_HALLWAY(RoomSide.DOOR, RoomSide.DOOR, RoomSide.BLOCKED, RoomSide.BLOCKED),
    CURVED_HALLWAY(RoomSide.DOOR, RoomSide.BLOCKED, RoomSide.DOOR, RoomSide.BLOCKED),
    ENTRANCE(RoomSide.BLOCKED, RoomSide.DOOR, RoomSide.DOOR, RoomSide.DOOR);

    RoomSidesData sides;
    List<RoomData> possibleRotations;

    RoomType(RoomSide SOUTH, RoomSide NORTH, RoomSide EAST, RoomSide WEST) {
        this.sides = new RoomSidesData(SOUTH, NORTH, EAST, WEST);

        this.possibleRotations = new ArrayList<>();

        RoomSidesData current = new RoomSidesData(SOUTH, NORTH, EAST, WEST);

        Rotation rotation = Rotation.NONE;

        possibleRotations.add(new RoomData(this, current, rotation));

        for (int i = 0; i < 4; i++) {
            rotation = rotation.add(Rotation.CLOCKWISE_90);
            current = new RoomSidesData(WEST, EAST, SOUTH, NORTH);
            possibleRotations.add(new RoomData(this, current, rotation));
        }
    }

    public List<RoomData> getPossibleFor(UnbuiltRoom room) {
        return possibleRotations.stream()
            .filter(x -> x.sides.matches(room.sides))
            .collect(Collectors.toList());
    }
}

// XOX
// OOO
// XOX