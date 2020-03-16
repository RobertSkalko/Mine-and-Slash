package com.robertx22.mine_and_slash.new_content.registry.rooms.base;

import com.robertx22.mine_and_slash.new_content.enums.RoomGroup;
import com.robertx22.mine_and_slash.new_content.enums.RoomType;
import com.robertx22.mine_and_slash.new_content.registry.DungeonRoom;
import com.robertx22.mine_and_slash.new_content.registry.rooms.RoomList;

public abstract class BaseRoomAdder {

    RoomGroup group;

    public BaseRoomAdder(RoomGroup group) {
        this.group = group;
    }

    public DungeonRoom add(String id, RoomType type) {
        DungeonRoom room = new DungeonRoom(id, type, group);

        RoomList.getRoomsINTERNAL()
            .add(room);

        return room;
    }

    public abstract void addAllRooms();

}
