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

    FOUR_WAY(RoomSide.DOOR, RoomSide.DOOR, RoomSide.DOOR, RoomSide.DOOR, "four_way") {
        @Override
        public List<RoomRotation> getRotations() {
            List<RoomRotation> all = new ArrayList<>();
            all.add(new RoomRotation(this, new RoomSides(RoomSide.DOOR, RoomSide.DOOR, RoomSide.DOOR, RoomSide.DOOR), Rotation.NONE));
            return all;
        }
    },
    STRAIGHT_HALLWAY(RoomSide.DOOR, RoomSide.DOOR, RoomSide.BLOCKED, RoomSide.BLOCKED, "straight_hallway") {
        @Override
        public List<RoomRotation> getRotations() {
            List<RoomRotation> all = new ArrayList<>();
            all.add(new RoomRotation(this, new RoomSides(RoomSide.DOOR, RoomSide.DOOR, RoomSide.BLOCKED, RoomSide.BLOCKED), Rotation.NONE));
            all.add(new RoomRotation(this, new RoomSides(RoomSide.BLOCKED, RoomSide.BLOCKED, RoomSide.DOOR, RoomSide.DOOR), Rotation.CLOCKWISE_90));
            all.add(new RoomRotation(this, new RoomSides(RoomSide.DOOR, RoomSide.DOOR, RoomSide.BLOCKED, RoomSide.BLOCKED), Rotation.CLOCKWISE_180));
            all.add(new RoomRotation(this, new RoomSides(RoomSide.BLOCKED, RoomSide.BLOCKED, RoomSide.DOOR, RoomSide.DOOR), Rotation.COUNTERCLOCKWISE_90));
            return all;
        }
    },
    CURVED_HALLWAY(RoomSide.DOOR, RoomSide.BLOCKED, RoomSide.DOOR, RoomSide.BLOCKED, "curved_hallway") {
        @Override
        public List<RoomRotation> getRotations() {
            List<RoomRotation> all = new ArrayList<>();
            all.add(new RoomRotation(this, new RoomSides(RoomSide.BLOCKED, RoomSide.DOOR, RoomSide.BLOCKED, RoomSide.DOOR), Rotation.NONE));
            all.add(new RoomRotation(this, new RoomSides(RoomSide.BLOCKED, RoomSide.DOOR, RoomSide.DOOR, RoomSide.BLOCKED), Rotation.CLOCKWISE_90));
            all.add(new RoomRotation(this, new RoomSides(RoomSide.DOOR, RoomSide.BLOCKED, RoomSide.DOOR, RoomSide.BLOCKED), Rotation.CLOCKWISE_180));
            all.add(new RoomRotation(this, new RoomSides(RoomSide.DOOR, RoomSide.BLOCKED, RoomSide.BLOCKED, RoomSide.DOOR), Rotation.COUNTERCLOCKWISE_90));
            return all;
        }

    },
    TRIPLE_HALLWAY(RoomSide.BLOCKED, RoomSide.DOOR, RoomSide.DOOR, RoomSide.DOOR, "triple_hallway") {
        @Override
        public List<RoomRotation> getRotations() {
            List<RoomRotation> all = new ArrayList<>();
            all.add(new RoomRotation(this, new RoomSides(RoomSide.BLOCKED, RoomSide.DOOR, RoomSide.DOOR, RoomSide.DOOR), Rotation.NONE));
            all.add(new RoomRotation(this, new RoomSides(RoomSide.DOOR, RoomSide.DOOR, RoomSide.DOOR, RoomSide.BLOCKED), Rotation.CLOCKWISE_90));
            all.add(new RoomRotation(this, new RoomSides(RoomSide.DOOR, RoomSide.BLOCKED, RoomSide.DOOR, RoomSide.DOOR), Rotation.CLOCKWISE_180));
            all.add(new RoomRotation(this, new RoomSides(RoomSide.DOOR, RoomSide.DOOR, RoomSide.BLOCKED, RoomSide.DOOR), Rotation.COUNTERCLOCKWISE_90));
            return all;
        }
    },
    END(RoomSide.DOOR, RoomSide.BLOCKED, RoomSide.BLOCKED, RoomSide.BLOCKED, "end") {
        @Override
        public List<RoomRotation> getRotations() {
            List<RoomRotation> all = new ArrayList<>();
            all.add(new RoomRotation(this, new RoomSides(RoomSide.DOOR, RoomSide.BLOCKED, RoomSide.BLOCKED, RoomSide.BLOCKED), Rotation.NONE));
            all.add(new RoomRotation(this, new RoomSides(RoomSide.BLOCKED, RoomSide.BLOCKED, RoomSide.BLOCKED, RoomSide.DOOR), Rotation.CLOCKWISE_90));
            all.add(new RoomRotation(this, new RoomSides(RoomSide.BLOCKED, RoomSide.DOOR, RoomSide.BLOCKED, RoomSide.BLOCKED), Rotation.CLOCKWISE_180));
            all.add(new RoomRotation(this, new RoomSides(RoomSide.BLOCKED, RoomSide.BLOCKED, RoomSide.DOOR, RoomSide.BLOCKED), Rotation.COUNTERCLOCKWISE_90));
            return all;
        }
    },
    ENTRANCE(RoomSide.BLOCKED, RoomSide.DOOR, RoomSide.BLOCKED, RoomSide.BLOCKED, "entrance") {
        @Override
        public List<RoomRotation> getRotations() {
            List<RoomRotation> all = new ArrayList<>();
            all.add(new RoomRotation(this, new RoomSides(RoomSide.DOOR, RoomSide.BLOCKED, RoomSide.BLOCKED, RoomSide.BLOCKED), Rotation.NONE));
            all.add(new RoomRotation(this, new RoomSides(RoomSide.BLOCKED, RoomSide.BLOCKED, RoomSide.BLOCKED, RoomSide.DOOR), Rotation.CLOCKWISE_90));
            all.add(new RoomRotation(this, new RoomSides(RoomSide.BLOCKED, RoomSide.DOOR, RoomSide.BLOCKED, RoomSide.BLOCKED), Rotation.CLOCKWISE_180));
            all.add(new RoomRotation(this, new RoomSides(RoomSide.BLOCKED, RoomSide.BLOCKED, RoomSide.DOOR, RoomSide.BLOCKED), Rotation.COUNTERCLOCKWISE_90));
            return all;
        }
    };

    public RoomSides sides;
    public String id;

    RoomType(RoomSide SOUTH, RoomSide NORTH, RoomSide EAST, RoomSide WEST, String id) {
        this.sides = new RoomSides(SOUTH, NORTH, EAST, WEST);
        this.id = id;

    }

    public abstract List<RoomRotation> getRotations();

    public List<RoomRotation> getPossibleFor(UnbuiltRoom room) {
        return getRotations().stream()
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