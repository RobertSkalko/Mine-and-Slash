package com.robertx22.mine_and_slash.new_content.registry.groups;

import java.util.Arrays;
import java.util.List;

public class TestGroup extends RoomGroup {

    public TestGroup() {
        super("test", 0);
    }

    @Override
    public List<RoomGroup> possibleOtherTypes() {
        return Arrays.asList();
    }
}
