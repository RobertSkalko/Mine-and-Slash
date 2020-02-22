package com.robertx22.mine_and_slash.new_content;

import com.robertx22.mine_and_slash.new_content.enums.RoomType;
import net.minecraft.util.Rotation;

public class RoomRotation {

    public RoomType type;
    public RoomSides sides;
    public Rotation rotation = Rotation.NONE;

    public RoomRotation(RoomType type, RoomSides sides, Rotation rotation) {
        this.type = type;
        this.sides = sides;
        this.rotation = rotation;
    }
}
