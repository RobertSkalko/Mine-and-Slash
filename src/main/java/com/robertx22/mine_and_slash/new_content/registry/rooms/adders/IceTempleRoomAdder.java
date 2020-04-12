package com.robertx22.mine_and_slash.new_content.registry.rooms.adders;

import com.robertx22.mine_and_slash.new_content.enums.RoomType;
import com.robertx22.mine_and_slash.new_content.registry.groups.RoomGroup;
import com.robertx22.mine_and_slash.new_content.registry.rooms.base.BaseRoomAdder;

public class IceTempleRoomAdder extends BaseRoomAdder {

    public IceTempleRoomAdder() {
        super(RoomGroup.ICE_TEMPLE);
    }

    @Override
    public void addAllRooms() {

        add("caverns", RoomType.CURVED_HALLWAY);
        add("ice_windows", RoomType.CURVED_HALLWAY);
        add("prayer_room", RoomType.CURVED_HALLWAY);

        add("boss_pool", RoomType.END).setBoss();
        add("evoker_boss", RoomType.END).setBoss();
        add("prison", RoomType.END);

        add("pit", RoomType.ENTRANCE);

        add("strongmobs", RoomType.FOUR_WAY);
        add("waterfall_stairway", RoomType.FOUR_WAY);
        add("ziggurat", RoomType.FOUR_WAY);

        add("arch", RoomType.STRAIGHT_HALLWAY);
        add("broken_bridge", RoomType.STRAIGHT_HALLWAY);

        add("altar", RoomType.TRIPLE_HALLWAY);
        add("fort_trader", RoomType.TRIPLE_HALLWAY);
        add("pool0", RoomType.TRIPLE_HALLWAY);

    }
}



