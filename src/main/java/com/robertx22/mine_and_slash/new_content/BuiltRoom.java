package com.robertx22.mine_and_slash.new_content;

import com.robertx22.mine_and_slash.new_content.registry.DungeonRoom;
import net.minecraft.util.ResourceLocation;

import java.util.Objects;

public class BuiltRoom {

    public RoomRotation data;
    public DungeonRoom room;

    public BuiltRoom(RoomRotation data, DungeonRoom room) {
        this.data = data;
        this.room = room;

    }

    public ResourceLocation getStructure() {
        return room.loc;
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
        return Objects.hash(room.toString(), data.rotation, data.type);
    }
}
