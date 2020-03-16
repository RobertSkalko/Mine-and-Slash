package com.robertx22.mine_and_slash.new_content.registry.groups;

import java.util.Arrays;
import java.util.List;

public class SteampunkGroup extends RoomGroup {

    public SteampunkGroup() {
        super("steampunk", 50);
        this.canBeMainTheme = false;
    }

    @Override
    public List<RoomGroup> possibleOtherTypes() {
        return Arrays.asList();
    }
}
