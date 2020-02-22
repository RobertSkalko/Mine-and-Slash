package com.robertx22.mine_and_slash.new_content;

import com.robertx22.mine_and_slash.mmorpg.Ref;
import net.minecraft.util.ResourceLocation;

public class DungeonRoom {

    ResourceLocation loc;
    RoomType type;

    public DungeonRoom(String id, RoomType type) {
        this.loc = new ResourceLocation(Ref.MODID, "dun/" + type.id + "/" + id);
        this.type = type;
    }

    public DungeonRoom setTest() {
        isTest = true;
        return this;
    }

    boolean isTest = false;

}
