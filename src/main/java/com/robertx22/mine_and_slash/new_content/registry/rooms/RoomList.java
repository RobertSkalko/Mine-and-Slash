package com.robertx22.mine_and_slash.new_content.registry.rooms;

import com.robertx22.mine_and_slash.new_content.enums.RoomType;
import com.robertx22.mine_and_slash.new_content.registry.DungeonRoom;
import com.robertx22.mine_and_slash.new_content.registry.groups.RoomGroup;
import com.robertx22.mine_and_slash.new_content.registry.rooms.adders.*;

import java.util.ArrayList;
import java.util.List;

public class RoomList {

    private static List<DungeonRoom> rooms = new ArrayList<>();

    public static List<DungeonRoom> getRoomsINTERNAL() {
        return rooms;
    }

    public static List<DungeonRoom> getAllRooms() {

        if (rooms.isEmpty()) {

            new NatureRoomAdder().addAllRooms();
            new SteampunkRoomAdder().addAllRooms();
            new TentRoomAdder().addAllRooms();
            new MineRoomAdder().addAllRooms();
            new StoneBrickRoomAdder().addAllRooms();
            new SandstoneRoomAdder().addAllRooms();
            new MossyBrickRoomAdder().addAllRooms();
            new SpruceMansionRoomAdder().addAllRooms();
            new BrickRoomAdder().addAllRooms();

            rooms.add(new DungeonRoom("hidden_button0", RoomType.CURVED_HALLWAY, RoomGroup.SANDSTONE));
            rooms.add(new DungeonRoom("tnt_pyramid", RoomType.END, RoomGroup.SANDSTONE));
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

        }

        return rooms;
    }

}
