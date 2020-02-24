package com.robertx22.mine_and_slash.new_content.registry;

import com.robertx22.mine_and_slash.new_content.enums.RoomGroup;
import com.robertx22.mine_and_slash.new_content.enums.RoomType;

import java.util.ArrayList;
import java.util.List;

public class RoomList {

    private static List<DungeonRoom> rooms = new ArrayList<>();

    public static List<DungeonRoom> getAllRooms() {

        if (rooms.isEmpty()) {
            rooms.add(new DungeonRoom("basic", RoomType.FOUR_WAY, RoomGroup.STONE_BRICK));
            rooms.add(new DungeonRoom("basic", RoomType.ENTRANCE, RoomGroup.STONE_BRICK));
            rooms.add(new DungeonRoom("boss", RoomType.END, RoomGroup.STONE_BRICK));
            rooms.add(new DungeonRoom("parkour", RoomType.TRIPLE_HALLWAY, RoomGroup.STONE_BRICK));
            rooms.add(new DungeonRoom("basic", RoomType.CURVED_HALLWAY, RoomGroup.STONE_BRICK));
            rooms.add(new DungeonRoom("basic", RoomType.STRAIGHT_HALLWAY, RoomGroup.STONE_BRICK));

            rooms.add(new DungeonRoom("simple_prismarine", RoomType.FOUR_WAY, RoomGroup.MISC));
            rooms.add(new DungeonRoom("prismarine", RoomType.ENTRANCE, RoomGroup.MISC));
            rooms.add(new DungeonRoom("nether0", RoomType.END, RoomGroup.MISC));
            rooms.add(new DungeonRoom("obsidian_lava0", RoomType.TRIPLE_HALLWAY, RoomGroup.MISC));
            rooms.add(new DungeonRoom("easy_sandstone_puzzle", RoomType.CURVED_HALLWAY, RoomGroup.MISC));
            rooms.add(new DungeonRoom("infested_cellar", RoomType.STRAIGHT_HALLWAY, RoomGroup.MISC));

        }

        return rooms;
    }

}
