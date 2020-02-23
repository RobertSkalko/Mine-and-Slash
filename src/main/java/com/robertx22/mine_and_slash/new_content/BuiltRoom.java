package com.robertx22.mine_and_slash.new_content;

import net.minecraft.util.ResourceLocation;

import java.util.Objects;

public class BuiltRoom {

    public RoomRotation data;
    public ResourceLocation structure;

    public BuiltRoom(RoomRotation data, ResourceLocation structure) {
        this.data = data;
        this.structure = structure;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BuiltRoom builtRoom = (BuiltRoom) o;
        return this.hashCode() == builtRoom.hashCode();
    }

    @Override
    public int hashCode() {
        return Objects.hash(structure.toString(), data.rotation, data.type);
    }
}
