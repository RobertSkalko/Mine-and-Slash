package com.robertx22.mine_and_slash.new_content.registry.groups;

import java.util.Arrays;
import java.util.List;

public class MossyBrickGroup extends RoomGroup {

    public MossyBrickGroup() {
        super("mossy_brick", 750);
    }

    @Override
    public List<RoomGroup> possibleOtherTypes() {
        return Arrays.asList(RoomGroup.STONE_BRICK);
    }
}

