package com.robertx22.mine_and_slash.new_content.enums;

import com.robertx22.mine_and_slash.new_content.RoomRotation;
import com.robertx22.mine_and_slash.new_content.RoomSides;
import com.robertx22.mine_and_slash.new_content.UnbuiltRoom;
import com.robertx22.mine_and_slash.uncommon.interfaces.IWeighted;
import net.minecraft.util.Rotation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public enum RoomType implements IWeighted {

    FOUR_WAY(RoomSide.DOOR, RoomSide.DOOR, RoomSide.DOOR, RoomSide.DOOR, "four_way"),
    STRAIGHT_HALLWAY(RoomSide.DOOR, RoomSide.DOOR, RoomSide.BLOCKED, RoomSide.BLOCKED, "straight_hallway"),
    CURVED_HALLWAY(RoomSide.DOOR, RoomSide.BLOCKED, RoomSide.DOOR, RoomSide.BLOCKED, "curved_hallway"),
    TRIPLE_HALLWAY(RoomSide.BLOCKED, RoomSide.DOOR, RoomSide.DOOR, RoomSide.DOOR, "triple_hallway"),
    END(RoomSide.DOOR, RoomSide.BLOCKED, RoomSide.BLOCKED, RoomSide.BLOCKED, "end"),
    ENTRANCE(RoomSide.BLOCKED, RoomSide.DOOR, RoomSide.BLOCKED, RoomSide.BLOCKED, "entrance");

    public RoomSides sides;
    List<RoomRotation> rotations;
    public String id;

    RoomType(RoomSide SOUTH, RoomSide NORTH, RoomSide EAST, RoomSide WEST, String id) {
        this.sides = new RoomSides(SOUTH, NORTH, EAST, WEST);
        this.id = id;

        this.rotations = new ArrayList<>();

        RoomSides current = new RoomSides(SOUTH, NORTH, EAST, WEST);

        Rotation rotation = Rotation.NONE;

        rotations.add(new RoomRotation(this, current, rotation));

        rotation = rotation.add(Rotation.CLOCKWISE_90);
        current = new RoomSides(WEST, EAST, SOUTH, NORTH);
        rotations.add(new RoomRotation(this, current, rotation));

        rotation = rotation.add(Rotation.CLOCKWISE_90);
        current = new RoomSides(NORTH, SOUTH, WEST, EAST);
        rotations.add(new RoomRotation(this, current, rotation));

        rotation = rotation.add(Rotation.CLOCKWISE_90);
        current = new RoomSides(EAST, WEST, NORTH, SOUTH);
        rotations.add(new RoomRotation(this, current, rotation));

    }

    public List<RoomRotation> getPossibleFor(UnbuiltRoom room) {
        return rotations.stream()
            .filter(x -> x.sides.matches(room.sides))
            .collect(Collectors.toList());
    }

    @Override
    public int Weight() {
        return 1000;
    }
}

// XOX
// OOO
// XOX