package com.robertx22.mine_and_slash.new_content.enums;

public enum RoomSide {
    DOOR("O"), BLOCKED("Z"), NONE("E");

    RoomSide(String debug) {
        this.debugString = debug;
    }

    public String debugString;

    public boolean canBeLinked(RoomSide side) {
        return this == NONE || side == NONE || side == this;
    }

}


