package com.robertx22.mine_and_slash.new_content.registry;

import com.robertx22.mine_and_slash.new_content.enums.RoomType;

import java.util.ArrayList;
import java.util.List;

public class RoomList {

    private static List<DungeonRoom> rooms = new ArrayList<>();

    public static List<DungeonRoom> getAllRooms() {

        if (rooms.isEmpty()) {
            rooms.add(new DungeonRoom("basic_stone_brick", RoomType.FOUR_WAY));
            rooms.add(new DungeonRoom("basic_stone_brick", RoomType.ENTRANCE));
            rooms.add(new DungeonRoom("stone_brick_boss", RoomType.END));
            rooms.add(new DungeonRoom("stone_brick_parkour0", RoomType.TRIPLE_HALLWAY));
            rooms.add(new DungeonRoom("simple_stone_brick", RoomType.CURVED_HALLWAY));
            rooms.add(new DungeonRoom("stone_brick0", RoomType.STRAIGHT_HALLWAY));
        }

        return rooms;
    }

}
