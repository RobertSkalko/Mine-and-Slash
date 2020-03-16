package com.robertx22.mine_and_slash.new_content.registry.rooms.adders;

import com.robertx22.mine_and_slash.new_content.enums.RoomGroup;
import com.robertx22.mine_and_slash.new_content.enums.RoomType;
import com.robertx22.mine_and_slash.new_content.registry.rooms.base.BaseRoomAdder;

public class BrickRoomAdder extends BaseRoomAdder {

    public BrickRoomAdder() {
        super(RoomGroup.BRICK);
    }

    @Override
    public void addAllRooms() {

        add("boss_exit_hidden_lever", RoomType.END).setBoss();
        add("trader0", RoomType.END).setTrader();

        add("0", RoomType.ENTRANCE);
        add("2", RoomType.ENTRANCE);

        add("hidden_ceiling", RoomType.FOUR_WAY);
        add("simple0", RoomType.FOUR_WAY);

        add("hidden_big_chest", RoomType.STRAIGHT_HALLWAY);
        add("lava_spiders", RoomType.STRAIGHT_HALLWAY);
        add("simple1", RoomType.STRAIGHT_HALLWAY);

        add("boss0", RoomType.TRIPLE_HALLWAY).setBoss();

    }
}
