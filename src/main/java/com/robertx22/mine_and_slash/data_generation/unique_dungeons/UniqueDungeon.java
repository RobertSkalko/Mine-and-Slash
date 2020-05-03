package com.robertx22.mine_and_slash.data_generation.unique_dungeons;

import com.google.gson.JsonObject;
import com.robertx22.mine_and_slash.new_content.building.DungeonUtils;
import com.robertx22.mine_and_slash.new_content.building.UniqueDungeonBuilder;
import com.robertx22.mine_and_slash.onevent.data_gen.ISerializable;
import com.robertx22.mine_and_slash.onevent.data_gen.ISerializedRegistryEntry;
import com.robertx22.mine_and_slash.registry.SlashRegistryType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Vec3i;

public class UniqueDungeon implements ISerializedRegistryEntry<UniqueDungeon>, ISerializable<UniqueDungeon> {

    String guid;
    public int lvl;
    public BlockPos entrancePos;
    int tier = 0;
    int weight = 1000;

    public static UniqueDungeon EMPTY = new UniqueDungeon("", 1, BlockPos.ZERO, 0);

    public UniqueDungeon(String guid, int lvl, BlockPos entrancePos, int tier) {
        this.guid = guid;
        this.lvl = lvl;
        this.tier = tier;
        this.entrancePos = entrancePos;
    }

    @Override
    public JsonObject toJson() {

        JsonObject json = new JsonObject();
        json.addProperty(ID, GUID());
        json.addProperty("level", lvl);
        json.addProperty("tier", tier);
        json.addProperty("weight", weight);

        JsonObject entrance = new JsonObject();
        entrance.addProperty("x", entrancePos.getX());
        entrance.addProperty("y", entrancePos.getY());
        entrance.addProperty("z", entrancePos.getZ());
        json.add("entrance_pos", entrance);

        return json;
    }

    @Override
    public UniqueDungeon fromJson(JsonObject json) {

        int lvl = json.get("level")
            .getAsInt();
        int tier = json.get("tier")
            .getAsInt();
        int weight = json.get("weight")
            .getAsInt();
        String guid = getGUIDFromJson(json);

        JsonObject entrance = json.getAsJsonObject("entrance_pos");
        int x = entrance.get("x")
            .getAsInt();
        int y = entrance.get("y")
            .getAsInt();
        int z = entrance.get("z")
            .getAsInt();

        return new UniqueDungeon(guid, lvl, new BlockPos(x, y, z), tier);
    }

    @Override
    public int getTier() {
        return tier;
    }

    @Override
    public SlashRegistryType getSlashRegistryType() {
        return SlashRegistryType.UNIQUE_DUNGEON;
    }

    @Override
    public String GUID() {
        return this.guid;
    }

    @Override
    public int Weight() {
        return weight;
    }

    public BlockPos getActualEntrancePosition(ChunkPos cpos) {

        ChunkPos start = DungeonUtils.getStartChunk(cpos);

        int off = (UniqueDungeonBuilder.SIZE / 2 - 2) * 16;

        BlockPos pos = start.asBlockPos();
        pos = pos.add(entrancePos.getX(), entrancePos.getY(), entrancePos.getZ());
        pos = pos.subtract(new Vec3i(off, 0, off));
        pos = pos.add(0, 50, 0);

        return pos;
    }
}
