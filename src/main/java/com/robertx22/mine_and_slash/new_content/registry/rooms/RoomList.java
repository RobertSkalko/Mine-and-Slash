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
            new SewersRoomAdder().addAllRooms();
            new NetherRoomAdder().addAllRooms();
            new IceTempleRoomAdder().addAllRooms();
            new WideNatureRoomAdder().addAllRooms();

            rooms.add(new DungeonRoom("simple_prismarine", RoomType.FOUR_WAY, RoomGroup.MISC));
            rooms.add(new DungeonRoom("prismarine", RoomType.ENTRANCE, RoomGroup.MISC));
            rooms.add(new DungeonRoom("obsidian_lava0", RoomType.TRIPLE_HALLWAY, RoomGroup.MISC));
            rooms.add(new DungeonRoom("easy_sandstone_puzzle", RoomType.CURVED_HALLWAY, RoomGroup.MISC));
            rooms.add(new DungeonRoom("infested_cellar", RoomType.STRAIGHT_HALLWAY, RoomGroup.MISC));

        }

        return rooms;
    }

}
