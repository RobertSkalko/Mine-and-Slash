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

        add("basement_boss", RoomType.CURVED_HALLWAY).setBoss();
        add("hanging_platforms", RoomType.CURVED_HALLWAY);
        add("zombie_boss_shrooms", RoomType.END).setBoss();
        add("camp_site", RoomType.ENTRANCE);
        add("big_tree", RoomType.FOUR_WAY);
        add("ambush_cave", RoomType.STRAIGHT_HALLWAY);
        add("winding_road", RoomType.STRAIGHT_HALLWAY);
        add("webhouse", RoomType.TRIPLE_HALLWAY);
        add("log", RoomType.TRIPLE_HALLWAY);
        add("spider_cave", RoomType.TRIPLE_HALLWAY);

    }
}


