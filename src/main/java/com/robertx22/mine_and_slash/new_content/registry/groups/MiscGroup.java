package com.robertx22.mine_and_slash.new_content.registry.groups;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MiscGroup extends RoomGroup {

    public MiscGroup() {
        super("misc", 10);
        this.canBeMainTheme = false;
    }

    @Override
    public List<RoomGroup> possibleOtherTypes() {
        return Arrays.asList();
    }

    @Override
    public RoomGroup getFallbackGroup(Random rand) {
        return RoomGroup.STONE_BRICK;
    }
}


