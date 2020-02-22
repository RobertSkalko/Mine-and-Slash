package com.robertx22.mine_and_slash.new_content;

import net.minecraft.util.Rotation;

public class RoomData {

    public RoomType type;
    RoomSidesData sides;
    Rotation rotation = Rotation.NONE;

    public RoomData(RoomType type, RoomSidesData sides, Rotation rotation) {
        this.type = type;
        this.sides = sides;
        this.rotation = rotation;
    }
}
