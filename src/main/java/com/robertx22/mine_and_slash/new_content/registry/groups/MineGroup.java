package com.robertx22.mine_and_slash.new_content.registry.groups;

import java.util.Arrays;
import java.util.List;

public class MineGroup extends RoomGroup {

    public MineGroup() {
        super("mine", 500);
    }

    @Override
    public List<RoomGroup> possibleOtherTypes() {
        return Arrays.asList();
    }
}
