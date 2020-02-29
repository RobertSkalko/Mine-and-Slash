package com.robertx22.mine_and_slash.new_content.registry;

import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.new_content.enums.RoomGroup;
import com.robertx22.mine_and_slash.new_content.enums.RoomType;
import com.robertx22.mine_and_slash.uncommon.interfaces.IWeighted;
import net.minecraft.util.ResourceLocation;

public class DungeonRoom implements IWeighted {

    public ResourceLocation loc;
    public RoomGroup group;
    public RoomType type;
    int weight = 1000;
    public boolean isBoss = false;

    public DungeonRoom(String id, RoomType type, RoomGroup group) {
        this.loc = new ResourceLocation(Ref.MODID, "dun/" + type.id + "/" + group.folder + "/" + id);
        this.type = type;
        this.group = group;
    }

    public DungeonRoom setTest() {
        isTest = true;
        return this;
    }

    public DungeonRoom setBoss() {
        this.isBoss = true;
        return this;
    }

    public DungeonRoom weight(int weight) {
        this.weight = weight;
        return this;
    }

    boolean isTest = false;

    @Override
    public int Weight() {
        return weight;
    }
}
