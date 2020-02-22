package com.robertx22.mine_and_slash.new_content.enums;

public enum RoomSide {
    DOOR, BLOCKED, NONE;

    public boolean canBeLinked(RoomSide side) {
        return this == NONE || side == NONE || side == this;
    }

}


