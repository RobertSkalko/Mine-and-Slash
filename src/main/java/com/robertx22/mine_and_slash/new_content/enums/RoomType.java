package com.robertx22.mine_and_slash.new_content.enums;

import com.robertx22.mine_and_slash.new_content.RoomRotation;
import com.robertx22.mine_and_slash.new_content.RoomSides;
import com.robertx22.mine_and_slash.new_content.UnbuiltRoom;
import com.robertx22.mine_and_slash.new_content.registry.DungeonRoom;
import com.robertx22.mine_and_slash.uncommon.interfaces.IWeighted;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import net.minecraft.util.Rotation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public enum RoomType implements IWeighted {

    FOUR_WAY("four_way") {
        @Override
        public List<RoomRotation> getRotations() {
            List<RoomRotation> all = new ArrayList<>();
            all.add(new RoomRotation(this, new RoomSides(RoomSide.DOOR, RoomSide.DOOR, RoomSide.DOOR, RoomSide.DOOR), Rotation.NONE));
            return all;
        }

        @Override
        public List<DungeonRoom> getAllRooms() {
            List<DungeonRoom> all = new ArrayList<>();

            return all;
        }
    },
    STRAIGHT_HALLWAY("straight_hallway") {
        @Override
        public List<RoomRotation> getRotations() {
            List<RoomRotation> all = new ArrayList<>();
            all.add(new RoomRotation(this, new RoomSides(RoomSide.DOOR, RoomSide.DOOR, RoomSide.BLOCKED, RoomSide.BLOCKED), Rotation.NONE));
            all.add(new RoomRotation(this, new RoomSides(RoomSide.BLOCKED, RoomSide.BLOCKED, RoomSide.DOOR, RoomSide.DOOR), Rotation.CLOCKWISE_90));
            all.add(new RoomRotation(this, new RoomSides(RoomSide.DOOR, RoomSide.DOOR, RoomSide.BLOCKED, RoomSide.BLOCKED), Rotation.CLOCKWISE_180));
            all.add(new RoomRotation(this, new RoomSides(RoomSide.BLOCKED, RoomSide.BLOCKED, RoomSide.DOOR, RoomSide.DOOR), Rotation.COUNTERCLOCKWISE_90));
            return all;
        }

        @Override
        public List<DungeonRoom> getAllRooms() {
            List<DungeonRoom> all = new ArrayList<>();

            return all;
        }
    },
    CURVED_HALLWAY("curved_hallway") {
        @Override
        public List<RoomRotation> getRotations() {
            List<RoomRotation> all = new ArrayList<>();
            all.add(new RoomRotation(this, new RoomSides(RoomSide.BLOCKED, RoomSide.DOOR, RoomSide.BLOCKED, RoomSide.DOOR), Rotation.NONE));
            all.add(new RoomRotation(this, new RoomSides(RoomSide.BLOCKED, RoomSide.DOOR, RoomSide.DOOR, RoomSide.BLOCKED), Rotation.CLOCKWISE_90));
            all.add(new RoomRotation(this, new RoomSides(RoomSide.DOOR, RoomSide.BLOCKED, RoomSide.DOOR, RoomSide.BLOCKED), Rotation.CLOCKWISE_180));
            all.add(new RoomRotation(this, new RoomSides(RoomSide.DOOR, RoomSide.BLOCKED, RoomSide.BLOCKED, RoomSide.DOOR), Rotation.COUNTERCLOCKWISE_90));
            return all;
        }

        @Override
        public List<DungeonRoom> getAllRooms() {
            List<DungeonRoom> all = new ArrayList<>();

            return all;
        }

    },
    TRIPLE_HALLWAY("triple_hallway") {
        @Override
        public List<RoomRotation> getRotations() {
            List<RoomRotation> all = new ArrayList<>();
            all.add(new RoomRotation(this, new RoomSides(RoomSide.BLOCKED, RoomSide.DOOR, RoomSide.DOOR, RoomSide.DOOR), Rotation.NONE));
            all.add(new RoomRotation(this, new RoomSides(RoomSide.DOOR, RoomSide.DOOR, RoomSide.DOOR, RoomSide.BLOCKED), Rotation.CLOCKWISE_90));
            all.add(new RoomRotation(this, new RoomSides(RoomSide.DOOR, RoomSide.BLOCKED, RoomSide.DOOR, RoomSide.DOOR), Rotation.CLOCKWISE_180));
            all.add(new RoomRotation(this, new RoomSides(RoomSide.DOOR, RoomSide.DOOR, RoomSide.BLOCKED, RoomSide.DOOR), Rotation.COUNTERCLOCKWISE_90));
            return all;
        }

        @Override
        public List<DungeonRoom> getAllRooms() {
            List<DungeonRoom> all = new ArrayList<>();

            return all;
        }
    },
    END("end") {
        @Override
        public List<RoomRotation> getRotations() {
            List<RoomRotation> all = new ArrayList<>();
            all.add(new RoomRotation(this, new RoomSides(RoomSide.DOOR, RoomSide.BLOCKED, RoomSide.BLOCKED, RoomSide.BLOCKED), Rotation.NONE));
            all.add(new RoomRotation(this, new RoomSides(RoomSide.BLOCKED, RoomSide.BLOCKED, RoomSide.BLOCKED, RoomSide.DOOR), Rotation.CLOCKWISE_90));
            all.add(new RoomRotation(this, new RoomSides(RoomSide.BLOCKED, RoomSide.DOOR, RoomSide.BLOCKED, RoomSide.BLOCKED), Rotation.CLOCKWISE_180));
            all.add(new RoomRotation(this, new RoomSides(RoomSide.BLOCKED, RoomSide.BLOCKED, RoomSide.DOOR, RoomSide.BLOCKED), Rotation.COUNTERCLOCKWISE_90));
            return all;
        }

        @Override
        public List<DungeonRoom> getAllRooms() {
            List<DungeonRoom> all = new ArrayList<>();

            return all;
        }
    },
    ENTRANCE("entrance") {
        @Override
        public List<RoomRotation> getRotations() {
            List<RoomRotation> all = new ArrayList<>();
            all.add(new RoomRotation(this, new RoomSides(RoomSide.DOOR, RoomSide.BLOCKED, RoomSide.BLOCKED, RoomSide.BLOCKED), Rotation.NONE));
            all.add(new RoomRotation(this, new RoomSides(RoomSide.BLOCKED, RoomSide.BLOCKED, RoomSide.BLOCKED, RoomSide.DOOR), Rotation.CLOCKWISE_90));
            all.add(new RoomRotation(this, new RoomSides(RoomSide.BLOCKED, RoomSide.DOOR, RoomSide.BLOCKED, RoomSide.BLOCKED), Rotation.CLOCKWISE_180));
            all.add(new RoomRotation(this, new RoomSides(RoomSide.BLOCKED, RoomSide.BLOCKED, RoomSide.DOOR, RoomSide.BLOCKED), Rotation.COUNTERCLOCKWISE_90));
            return all;
        }

        @Override
        public List<DungeonRoom> getAllRooms() {
            List<DungeonRoom> all = new ArrayList<>();

            return all;
        }
    };

    public RoomSides sides;
    public String id;

    RoomType(String id) {
        this.id = id;
    }

    public final DungeonRoom getTestRoom() {
        return new DungeonRoom("test", this).setTest();
    }

    public abstract List<RoomRotation> getRotations();

    public abstract List<DungeonRoom> getAllRooms();

    public DungeonRoom getRandomRoom(Random rand) {
        return getRandomRoom(rand, false);
    }

    public DungeonRoom getRandomRoom(Random rand, boolean debug) {
        if (debug) {
            return this.getTestRoom();
        } else {
            return RandomUtils.weightedRandom(getAllRooms(), rand.nextDouble());
        }
    }

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