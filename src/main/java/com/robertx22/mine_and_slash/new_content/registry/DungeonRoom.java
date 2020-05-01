package com.robertx22.mine_and_slash.new_content.registry;

import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.new_content.data_processors.bases.SpawnedMob;
import com.robertx22.mine_and_slash.new_content.enums.RoomType;
import com.robertx22.mine_and_slash.new_content.registry.groups.RoomGroup;
import com.robertx22.mine_and_slash.uncommon.interfaces.IWeighted;
import net.minecraft.util.ResourceLocation;

public class DungeonRoom implements IWeighted {

    public ResourceLocation loc;
    public RoomGroup group;
    public RoomType type;
    int weight = 1000;
    public boolean isBoss = false;
    public boolean isPuzzleBlock = false;
    public boolean isTrader = false;

    public DungeonRoom(String id, RoomType type, RoomGroup group) {
        this.loc = new ResourceLocation(Ref.MODID, "dun/" + group.folder + "/" + type.id + "/" + id);
        this.type = type;
        this.group = group;
    }

    public DungeonRoom(ResourceLocation loc) {
        this.loc = loc;
        this.type = RoomType.ENTRANCE;
        this.group = RoomGroup.TEST;
    }

    public boolean canSpawnMob(SpawnedMob mob) {
        return group.canSpawnMob(mob);
    }

    public DungeonRoom setTest() {
        isTest = true;
        return this;
    }

    public DungeonRoom setPuzzleBlock() {
        isPuzzleBlock = true;
        return this;
    }

    public DungeonRoom setBoss() {
        this.isBoss = true;
        return this;
    }

    public DungeonRoom setTrader() {
        this.isTrader = true;
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
