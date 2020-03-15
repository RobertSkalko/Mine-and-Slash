package com.robertx22.mine_and_slash.new_content.registry.rooms;

import com.robertx22.mine_and_slash.new_content.enums.RoomGroup;
import com.robertx22.mine_and_slash.new_content.enums.RoomType;
import com.robertx22.mine_and_slash.new_content.registry.DungeonRoom;

public class RoomsHelper {

    RoomGroup group;

    public RoomsHelper(RoomGroup group) {
        this.group = group;
    }

    public void add(String id, RoomType type) {
        RoomList.getRoomsINTERNAL()
            .add(new DungeonRoom(id, type, group));
    }

}
