package com.robertx22.mine_and_slash.new_content.registry.rooms.adders;

import com.robertx22.mine_and_slash.new_content.enums.RoomType;
import com.robertx22.mine_and_slash.new_content.registry.groups.RoomGroup;
import com.robertx22.mine_and_slash.new_content.registry.rooms.base.BaseRoomAdder;

public class SpruceMansionRoomAdder extends BaseRoomAdder {

    public SpruceMansionRoomAdder() {
        super(RoomGroup.SPRUCE_MANSION);
    }

    @Override
    public void addAllRooms() {

        add("2", RoomType.CURVED_HALLWAY);

        add("boss_bedroom", RoomType.END).setBoss();
        add("boss_chest_behind_throne", RoomType.END).setBoss();
        add("boss0", RoomType.END).setBoss();
        add("parkour_hidden_slime", RoomType.END);
        add("slime_parkour_hidden", RoomType.END);
        add("under_snow_room", RoomType.END);

        add("0", RoomType.ENTRANCE);

        add("2", RoomType.FOUR_WAY);
        add("ice_pillar", RoomType.FOUR_WAY);
        add("redstone_ore_secret", RoomType.FOUR_WAY);

        add("1", RoomType.STRAIGHT_HALLWAY);
        add("blocked_but_under_snow", RoomType.STRAIGHT_HALLWAY);
        add("crouching", RoomType.STRAIGHT_HALLWAY);
        add("dangerous_underground", RoomType.STRAIGHT_HALLWAY);
        add("peek_secret_ice", RoomType.STRAIGHT_HALLWAY);
        add("simple3", RoomType.STRAIGHT_HALLWAY);

        add("hidden_trapdoor_room", RoomType.TRIPLE_HALLWAY);

    }
}
