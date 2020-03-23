package com.robertx22.mine_and_slash.new_content.registry.groups;

import java.util.Arrays;
import java.util.List;

public class NetherGroup extends RoomGroup {

    public NetherGroup() {
        super("nether", 50099999);
        this.canBeMainTheme = true;
        this.canSpawnFireMobs = true;
    }

    @Override
    public List<RoomGroup> possibleOtherTypes() {
        return Arrays.asList();
    }
}
