package com.robertx22.mine_and_slash.new_content.registry.rooms.adders;

import com.robertx22.mine_and_slash.new_content.enums.RoomType;
import com.robertx22.mine_and_slash.new_content.registry.groups.RoomGroup;
import com.robertx22.mine_and_slash.new_content.registry.rooms.base.BaseRoomAdder;

public class WideNatureRoomAdder extends BaseRoomAdder {

    public WideNatureRoomAdder() {
        super(RoomGroup.WIDE_NATURE);
    }

    @Override
    public void addAllRooms() {

        add("pond", RoomType.CURVED_HALLWAY);
        add("cave0", RoomType.END);
        add("roses", RoomType.ENTRANCE);
        add("crossroad0", RoomType.FOUR_WAY);
        add("tunnel", RoomType.STRAIGHT_HALLWAY);
        add("webhouse", RoomType.TRIPLE_HALLWAY);

    }
}


