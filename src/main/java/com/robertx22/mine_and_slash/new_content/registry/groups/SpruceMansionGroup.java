package com.robertx22.mine_and_slash.new_content.registry.groups;

import java.util.Arrays;
import java.util.List;

public class SpruceMansionGroup extends RoomGroup {

    public SpruceMansionGroup() {
        super("spruce_mansion", 700);
    }

    @Override
    public List<RoomGroup> possibleOtherTypes() {
        return Arrays.asList();
    }
}
