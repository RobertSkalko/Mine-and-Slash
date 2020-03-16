package com.robertx22.mine_and_slash.new_content.registry.rooms.adders;

import com.robertx22.mine_and_slash.new_content.enums.RoomGroup;
import com.robertx22.mine_and_slash.new_content.enums.RoomType;
import com.robertx22.mine_and_slash.new_content.registry.rooms.base.BaseRoomAdder;

public class MineRoomAdder extends BaseRoomAdder {

    public MineRoomAdder() {
        super(RoomGroup.MINE);
    }

    @Override
    public void addAllRooms() {

        add("nasty_lava_parkour", RoomType.CURVED_HALLWAY);

        add("simple2", RoomType.ENTRANCE);

        add("shaft0", RoomType.FOUR_WAY);
        add("trader", RoomType.FOUR_WAY).setTrader();

        add("parkour0", RoomType.STRAIGHT_HALLWAY);

        add("redstone_ore_hidden_room", RoomType.TRIPLE_HALLWAY);

    }
}
