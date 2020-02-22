package com.robertx22.mine_and_slash.new_content;

import net.minecraft.util.ResourceLocation;

public class BuiltRoom {

    public RoomRotation data;
    public ResourceLocation structure;

    public BuiltRoom(RoomRotation data, ResourceLocation structure) {
        this.data = data;
        this.structure = structure;
    }
}
