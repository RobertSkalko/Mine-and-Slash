package com.robertx22.mine_and_slash.new_content.registry.groups;

import java.util.Arrays;
import java.util.List;

public class StoneBrickGroup extends RoomGroup {

    public StoneBrickGroup() {
        super("stone_brick", 1000);
    }

    @Override
    public List<RoomGroup> possibleOtherTypes() {
        return Arrays.asList(RoomGroup.MOSSY_BRICK, RoomGroup.BRICK, RoomGroup.STEAMPUNK);
    }

}
