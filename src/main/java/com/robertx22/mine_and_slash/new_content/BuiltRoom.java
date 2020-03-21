package com.robertx22.mine_and_slash.new_content;

import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.new_content.enums.RoomSide;
import com.robertx22.mine_and_slash.new_content.enums.RoomType;
import com.robertx22.mine_and_slash.new_content.registry.DungeonRoom;
import com.robertx22.mine_and_slash.new_content.registry.groups.RoomGroup;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;

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

    public static BuiltRoom getBarrier() {
        DungeonRoom barrier = new DungeonRoom("", RoomType.END, RoomGroup.TEST);
        barrier.loc = new ResourceLocation(Ref.MODID, "dun/barrier");
        RoomRotation rot = new RoomRotation(RoomType.END, new RoomSides(RoomSide.BLOCKED, RoomSide.BLOCKED, RoomSide.BLOCKED, RoomSide.BLOCKED), Rotation.NONE);
        BuiltRoom built = new BuiltRoom(rot, barrier);
        return built;
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
