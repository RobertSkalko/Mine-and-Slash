package com.robertx22.mine_and_slash.new_content;

import com.robertx22.mine_and_slash.new_content.enums.RoomSide;

public class RoomSides {

    public RoomSide SOUTH;
    public RoomSide NORTH;
    public RoomSide EAST;
    public RoomSide WEST;

    public RoomSides(RoomSide SOUTH, RoomSide NORTH, RoomSide EAST, RoomSide WEST) {
        this.SOUTH = SOUTH;
        this.NORTH = NORTH;
        this.EAST = EAST;
        this.WEST = WEST;
    }

    public boolean matches(RoomSides data) {
        return SOUTH.canBeLinked(data.SOUTH) && NORTH.canBeLinked(data.NORTH) && EAST.canBeLinked(data.EAST) && WEST.canBeLinked(data.WEST);
    }
}
