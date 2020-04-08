package com.robertx22.mine_and_slash.new_content.registry.groups;

import java.util.Arrays;
import java.util.List;

public class WideNatureRoom extends RoomGroup {

    public WideNatureRoom() {
        super("wn", 1000);
        this.canSpawnFireMobs = false;
    }

    @Override
    public List<RoomGroup> possibleOtherTypes() {
        return Arrays.asList();
    }
}

