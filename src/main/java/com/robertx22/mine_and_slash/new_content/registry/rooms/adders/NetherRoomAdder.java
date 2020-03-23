package com.robertx22.mine_and_slash.new_content.registry.rooms.adders;

import com.robertx22.mine_and_slash.new_content.enums.RoomType;
import com.robertx22.mine_and_slash.new_content.registry.groups.RoomGroup;
import com.robertx22.mine_and_slash.new_content.registry.rooms.base.BaseRoomAdder;

public class NetherRoomAdder extends BaseRoomAdder {

    public NetherRoomAdder() {
        super(RoomGroup.NETHER);
    }

    @Override
    public void addAllRooms() {

        add("climb_to_treasure", RoomType.CURVED_HALLWAY);
        add("the_witch", RoomType.CURVED_HALLWAY);

        add("boss2", RoomType.END).setBoss();
        add("nether0", RoomType.END);

        add("0", RoomType.ENTRANCE);

        add("blazes", RoomType.FOUR_WAY);
        add("wart_farm", RoomType.FOUR_WAY);
        add("tower_of_doom", RoomType.FOUR_WAY);

        add("lava_detour", RoomType.STRAIGHT_HALLWAY);
        add("skeletons0", RoomType.STRAIGHT_HALLWAY);

        add("dangerous_lava", RoomType.TRIPLE_HALLWAY);
        add("easy0", RoomType.TRIPLE_HALLWAY);

    }
}

