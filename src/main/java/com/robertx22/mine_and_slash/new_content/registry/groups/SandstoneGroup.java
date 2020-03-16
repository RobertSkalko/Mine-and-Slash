package com.robertx22.mine_and_slash.new_content.registry.groups;

import java.util.Arrays;
import java.util.List;

public class SandstoneGroup extends RoomGroup {

    public SandstoneGroup() {
        super("sandstone", 800);
        this.canSpawnFireMobs = true;
    }

    @Override
    public List<RoomGroup> possibleOtherTypes() {
        return Arrays.asList();
    }
}
