package com.robertx22.mine_and_slash.new_content.registry.rooms.adders;

import com.robertx22.mine_and_slash.new_content.enums.RoomGroup;
import com.robertx22.mine_and_slash.new_content.enums.RoomType;
import com.robertx22.mine_and_slash.new_content.registry.rooms.base.BaseRoomAdder;

public class SandstoneRoomAdder extends BaseRoomAdder {

    public SandstoneRoomAdder() {
        super(RoomGroup.SANDSTONE);
    }

    @Override
    public void addAllRooms() {

        add("boss_and_skellies", RoomType.END);

        add("treasure0", RoomType.FOUR_WAY);

        add("treasure_lava_pyramid", RoomType.STRAIGHT_HALLWAY);

        add("dorm0", RoomType.TRIPLE_HALLWAY);

    }
}

