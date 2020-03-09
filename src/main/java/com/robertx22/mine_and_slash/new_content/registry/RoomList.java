package com.robertx22.mine_and_slash.new_content.registry;

import com.robertx22.mine_and_slash.new_content.enums.RoomGroup;
import com.robertx22.mine_and_slash.new_content.enums.RoomType;

import java.util.ArrayList;
import java.util.List;

public class RoomList {

    private static List<DungeonRoom> rooms = new ArrayList<>();

    public static List<DungeonRoom> getAllRooms() {

        if (rooms.isEmpty()) {

            rooms.add(new DungeonRoom("hidden_button2", RoomType.TRIPLE_HALLWAY, RoomGroup.STONE_BRICK));
            rooms.add(new DungeonRoom("boss_trap", RoomType.STRAIGHT_HALLWAY, RoomGroup.STONE_BRICK).setBoss());

            rooms.add(new DungeonRoom("slime_puzzle", RoomType.CURVED_HALLWAY, RoomGroup.STONE_BRICK));
            rooms.add(new DungeonRoom("hidden_lever0", RoomType.END, RoomGroup.STONE_BRICK));
            rooms.add(new DungeonRoom("simple1", RoomType.FOUR_WAY, RoomGroup.STONE_BRICK));
            rooms.add(new DungeonRoom("simple2", RoomType.STRAIGHT_HALLWAY, RoomGroup.STONE_BRICK));
            rooms.add(new DungeonRoom("puzzle_plus_web", RoomType.TRIPLE_HALLWAY, RoomGroup.STONE_BRICK).setPuzzleBlock());

            rooms.add(new DungeonRoom("basic", RoomType.FOUR_WAY, RoomGroup.STONE_BRICK));
            rooms.add(new DungeonRoom("basic", RoomType.ENTRANCE, RoomGroup.STONE_BRICK));
            rooms.add(new DungeonRoom("boss", RoomType.END, RoomGroup.STONE_BRICK).setBoss());
            rooms.add(new DungeonRoom("parkour", RoomType.TRIPLE_HALLWAY, RoomGroup.STONE_BRICK));
            rooms.add(new DungeonRoom("basic", RoomType.CURVED_HALLWAY, RoomGroup.STONE_BRICK));
            rooms.add(new DungeonRoom("basic", RoomType.STRAIGHT_HALLWAY, RoomGroup.STONE_BRICK));

            rooms.add(new DungeonRoom("simple_trap0", RoomType.CURVED_HALLWAY, RoomGroup.STONE_BRICK));
            rooms.add(new DungeonRoom("simple_prison0", RoomType.END, RoomGroup.STONE_BRICK));
            rooms.add(new DungeonRoom("simple0", RoomType.FOUR_WAY, RoomGroup.STONE_BRICK));
            rooms.add(new DungeonRoom("simple0", RoomType.STRAIGHT_HALLWAY, RoomGroup.STONE_BRICK));
            rooms.add(new DungeonRoom("simple_maze0", RoomType.TRIPLE_HALLWAY, RoomGroup.STONE_BRICK).weight(200));

            rooms.add(new DungeonRoom("trap_puzzle0", RoomType.CURVED_HALLWAY, RoomGroup.SANDSTONE));
            rooms.add(new DungeonRoom("trap_prison0", RoomType.END, RoomGroup.SANDSTONE));
            //rooms.add(new DungeonRoom("basic", RoomType.ENTRANCE, RoomGroup.STONE_BRICK));
            rooms.add(new DungeonRoom("simple0", RoomType.FOUR_WAY, RoomGroup.SANDSTONE));
            rooms.add(new DungeonRoom("simple0", RoomType.STRAIGHT_HALLWAY, RoomGroup.SANDSTONE));
            rooms.add(new DungeonRoom("simple_maze0", RoomType.TRIPLE_HALLWAY, RoomGroup.SANDSTONE).weight(200));

            rooms.add(new DungeonRoom("hidden_button0", RoomType.CURVED_HALLWAY, RoomGroup.SANDSTONE));
            rooms.add(new DungeonRoom("tnt_pyramid", RoomType.END, RoomGroup.SANDSTONE));
            //rooms.add(new DungeonRoom("basic", RoomType.ENTRANCE, RoomGroup.STONE_BRICK));
            rooms.add(new DungeonRoom("simple1", RoomType.FOUR_WAY, RoomGroup.SANDSTONE));
            rooms.add(new DungeonRoom("simple_magma0", RoomType.STRAIGHT_HALLWAY, RoomGroup.SANDSTONE));
            rooms.add(new DungeonRoom("parkour_puzzle_magma", RoomType.TRIPLE_HALLWAY, RoomGroup.SANDSTONE));

            rooms.add(new DungeonRoom("simple_prismarine", RoomType.FOUR_WAY, RoomGroup.MISC));
            rooms.add(new DungeonRoom("prismarine", RoomType.ENTRANCE, RoomGroup.MISC));
            rooms.add(new DungeonRoom("nether0", RoomType.END, RoomGroup.MISC));
            rooms.add(new DungeonRoom("obsidian_lava0", RoomType.TRIPLE_HALLWAY, RoomGroup.MISC));
            rooms.add(new DungeonRoom("easy_sandstone_puzzle", RoomType.CURVED_HALLWAY, RoomGroup.MISC));
            rooms.add(new DungeonRoom("infested_cellar", RoomType.STRAIGHT_HALLWAY, RoomGroup.MISC));

            rooms.add(new DungeonRoom("sewers", RoomType.FOUR_WAY, RoomGroup.MISC));
            rooms.add(new DungeonRoom("sewers", RoomType.ENTRANCE, RoomGroup.MISC));
            rooms.add(new DungeonRoom("sewers_treasure", RoomType.END, RoomGroup.MISC).weight(300));
            rooms.add(new DungeonRoom("sewers_puzzle_easy", RoomType.TRIPLE_HALLWAY, RoomGroup.MISC));
            rooms.add(new DungeonRoom("sewers", RoomType.CURVED_HALLWAY, RoomGroup.MISC));
            rooms.add(new DungeonRoom("sewers", RoomType.STRAIGHT_HALLWAY, RoomGroup.MISC));

            rooms.add(new DungeonRoom("trap", RoomType.FOUR_WAY, RoomGroup.SANDSTONE));
            rooms.add(new DungeonRoom("basic", RoomType.ENTRANCE, RoomGroup.SANDSTONE));
            rooms.add(new DungeonRoom("boss", RoomType.END, RoomGroup.SANDSTONE).setBoss());
            rooms.add(new DungeonRoom("cactus", RoomType.TRIPLE_HALLWAY, RoomGroup.SANDSTONE));
            rooms.add(new DungeonRoom("prison", RoomType.CURVED_HALLWAY, RoomGroup.SANDSTONE));
            rooms.add(new DungeonRoom("puzzle0", RoomType.STRAIGHT_HALLWAY, RoomGroup.SANDSTONE));

        }

        return rooms;
    }

}
