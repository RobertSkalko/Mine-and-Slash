package com.robertx22.mine_and_slash.new_content.registry.rooms.adders;

import com.robertx22.mine_and_slash.new_content.enums.RoomType;
import com.robertx22.mine_and_slash.new_content.registry.groups.RoomGroup;
import com.robertx22.mine_and_slash.new_content.registry.rooms.base.BaseRoomAdder;

public class SewersRoomAdder extends BaseRoomAdder {

    public SewersRoomAdder() {
        super(RoomGroup.SEWERS);
    }

    @Override
    public void addAllRooms() {

        add("sewers", RoomType.FOUR_WAY);
        add("sewers", RoomType.ENTRANCE);
        add("sewers_treasure", RoomType.END).weight(300);
        add("sewers_puzzle_easy", RoomType.TRIPLE_HALLWAY);
        add("sewers", RoomType.CURVED_HALLWAY);
        add("sewers", RoomType.STRAIGHT_HALLWAY);

    }
}


