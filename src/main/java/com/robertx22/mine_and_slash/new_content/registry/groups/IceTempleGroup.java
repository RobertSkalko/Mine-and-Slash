package com.robertx22.mine_and_slash.new_content.registry.groups;

import java.util.Arrays;
import java.util.List;

public class IceTempleGroup extends RoomGroup {

    public IceTempleGroup() {
        super("it", 1000);
        this.canSpawnFireMobs = false;
    }

    @Override
    public List<RoomGroup> possibleOtherTypes() {
        return Arrays.asList();
    }
}

