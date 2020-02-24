package com.robertx22.mine_and_slash.new_content.enums;

import com.robertx22.mine_and_slash.uncommon.interfaces.IWeighted;

public enum RoomGroup implements IWeighted {

    STONE_BRICK("stone_brick", 1000),
    MISC("misc", 10);

    RoomGroup(String folder, int weight) {
        this.folder = folder;
        this.weight = weight;
    }

    public String folder;
    int weight;

    @Override
    public int Weight() {
        return weight;
    }
}
