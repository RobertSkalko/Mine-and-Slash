package com.robertx22.mine_and_slash.new_content.registry.rooms.adders;

import com.robertx22.mine_and_slash.new_content.enums.RoomType;
import com.robertx22.mine_and_slash.new_content.registry.groups.RoomGroup;
import com.robertx22.mine_and_slash.new_content.registry.rooms.base.BaseRoomAdder;

public class MineRoomAdder extends BaseRoomAdder {

    public MineRoomAdder() {
        super(RoomGroup.MINESHAFT);
    }

    @Override
    public void addAllRooms() {

        add("boss1", RoomType.END).setBoss();

        add("nasty_lava_parkour", RoomType.CURVED_HALLWAY);

        add("simple2", RoomType.ENTRANCE);

        add("shaft0", RoomType.FOUR_WAY);
        add("huge_pit", RoomType.FOUR_WAY);
        add("trader", RoomType.FOUR_WAY).setTrader();

        add("parkour0", RoomType.STRAIGHT_HALLWAY);
        add("chest_down_pit", RoomType.STRAIGHT_HALLWAY);

        add("redstone_ore_hidden_room", RoomType.TRIPLE_HALLWAY);
        add("simple2", RoomType.TRIPLE_HALLWAY);

        add("lava_boss_room", RoomType.END);
        add("infested_house", RoomType.CURVED_HALLWAY);
        add("hideout", RoomType.CURVED_HALLWAY);
        add("tight_mine", RoomType.STRAIGHT_HALLWAY);
        add("train_station", RoomType.TRIPLE_HALLWAY);

    }
}
