package com.robertx22.mine_and_slash.new_content.registry;

import com.robertx22.mine_and_slash.new_content.enums.RoomType;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class RoomsList {

    public static DungeonRoom ENTRANCE_TEST = new DungeonRoom("test", RoomType.ENTRANCE).setTest();
    public static DungeonRoom END_TEST = new DungeonRoom("test", RoomType.END).setTest();
    public static DungeonRoom STRAIGHT_HALLWAY_TEST = new DungeonRoom("test", RoomType.STRAIGHT_HALLWAY).setTest();
    public static DungeonRoom TRIPLE_HALLWAY_TEST = new DungeonRoom("test", RoomType.TRIPLE_HALLWAY).setTest();
    public static DungeonRoom FOUR_WAY_TEST = new DungeonRoom("test", RoomType.FOUR_WAY).setTest();
    public static DungeonRoom CURVED_HALLWAY_TEST = new DungeonRoom("test", RoomType.CURVED_HALLWAY).setTest();

    private static List<DungeonRoom> all = new ArrayList<>();

    public static List<DungeonRoom> getAllRooms() {

        if (all == null || all.isEmpty()) {
            all.add(ENTRANCE_TEST);
            all.add(END_TEST);
            all.add(STRAIGHT_HALLWAY_TEST);
            all.add(TRIPLE_HALLWAY_TEST);
            all.add(FOUR_WAY_TEST);
            all.add(CURVED_HALLWAY_TEST);

        }
        return all;
    }

    public static RoomType randomRoomType(List<RoomType> list, Random rand) {
        return RandomUtils.weightedRandom(list, rand.nextDouble());
    }

    public static DungeonRoom randomDungeonRoom(List<DungeonRoom> list, Random rand) {
        return RandomUtils.weightedRandom(list, rand.nextDouble());
    }

    public static List<DungeonRoom> getAllOfType(RoomType type) {
        return getAllRooms().stream()
            .filter(x -> x.type.equals(type))
            .collect(Collectors.toList());
    }
}