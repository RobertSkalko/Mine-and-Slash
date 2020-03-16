package com.robertx22.mine_and_slash.new_content.registry.groups;

import java.util.Arrays;
import java.util.List;

public class TentGroup extends RoomGroup {

    public TentGroup() {
        super("tent", 100);
    }

    @Override
    public List<RoomGroup> possibleOtherTypes() {
        return Arrays.asList();
    }

}
