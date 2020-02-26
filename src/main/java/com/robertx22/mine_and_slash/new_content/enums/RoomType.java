package com.robertx22.mine_and_slash.new_content.enums;

import com.robertx22.mine_and_slash.new_content.RoomRotation;
import com.robertx22.mine_and_slash.new_content.RoomSides;
import com.robertx22.mine_and_slash.new_content.UnbuiltRoom;
import com.robertx22.mine_and_slash.new_content.registry.DungeonRoom;
import com.robertx22.mine_and_slash.new_content.registry.RoomList;
import com.robertx22.mine_and_slash.uncommon.interfaces.IWeighted;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import net.minecraft.util.Rotation;

import java.util.ArrayList;
import java.util.Arrays;
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

    };

    public RoomSides sides;
    public String id;

    RoomType(String id) {
        this.id = id;
    }

    public final DungeonRoom getTestRoom() {
        return new DungeonRoom("test", this, RoomGroup.TEST).setTest();
    }

    public abstract List<RoomRotation> getRotations();

    public final List<DungeonRoom> getAllRooms() {
        return RoomList.getAllRooms()
            .stream()
            .filter(x -> x.type.equals(this))
            .collect(Collectors.toList());

    }

    public DungeonRoom getRandomRoom(RoomGroup group, Random rand) {
        return getRandomRoom(group, rand, false);
    }

    public DungeonRoom getRandomRoom(RoomGroup group, Random rand, boolean debug) {
        if (debug) {
            return this.getTestRoom();
        } else {
            RoomGroup g = group;

            if (RandomUtils.roll(20, rand)) {
                g = RoomGroup.MISC;
            } else if (RandomUtils.roll(5, rand)) {

                List<RoomGroup> posGroups = new ArrayList<>();
                Arrays.stream(RoomGroup.values())
                    .forEach(x -> {
                        if (x != RoomGroup.MISC && x != group) {
                            posGroups.add(x);
                        }
                    });

                g = RandomUtils.randomFromList(posGroups, rand);
            }

            List<DungeonRoom> possible = new ArrayList<>();
            for (DungeonRoom x : getAllRooms()) {
                if (x.group.equals(g)) {
                    possible.add(x);
                }
            }

            return RandomUtils.weightedRandom(possible, rand.nextDouble());

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