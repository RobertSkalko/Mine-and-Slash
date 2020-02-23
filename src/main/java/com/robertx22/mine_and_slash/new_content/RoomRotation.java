package com.robertx22.mine_and_slash.new_content;

import com.robertx22.mine_and_slash.new_content.enums.RoomType;
import com.robertx22.mine_and_slash.uncommon.interfaces.IWeighted;
import net.minecraft.util.Rotation;

public class RoomRotation implements IWeighted {

    public RoomType type;
    public RoomSides sides;
    public Rotation rotation;

    public RoomRotation(RoomType type, RoomSides sides, Rotation rotation) {
        this.type = type;
        this.sides = sides;
        this.rotation = rotation;
    }

    @Override
    public int Weight() {
        return 1000;
    }
}
