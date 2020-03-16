package com.robertx22.mine_and_slash.new_content.registry.groups;

import com.robertx22.mine_and_slash.uncommon.interfaces.IWeighted;

import java.util.ArrayList;
import java.util.List;

public abstract class RoomGroup implements IWeighted {

    public static RoomGroup TEST = new TestGroup();

    public static RoomGroup MOSSY_BRICK = new MossyBrickGroup();
    public static RoomGroup STONE_BRICK = new StoneBrickGroup();
    public static RoomGroup SANDSTONE = new SandstoneGroup();
    public static RoomGroup SPRUCE_MANSION = new SpruceMansionGroup();
    public static RoomGroup MINESHAFT = new MineGroup();
    public static RoomGroup BRICK = new BrickGroup();
    public static RoomGroup TENT = new TentGroup();
    public static RoomGroup STEAMPUNK = new SteampunkGroup();
    public static RoomGroup NATURE = new NatureGroup();
    public static RoomGroup MISC = new MiscGroup();
    public static RoomGroup NETHER = new NetherGroup();

    private static List<RoomGroup> all = new ArrayList<>();

    public static List<RoomGroup> getAll() {

        if (all.isEmpty()) {

            all.add(MOSSY_BRICK);
            all.add(TEST);
            all.add(STONE_BRICK);
            all.add(SANDSTONE);
            all.add(SPRUCE_MANSION);
            all.add(MINESHAFT);
            all.add(BRICK);
            all.add(TENT);
            all.add(STEAMPUNK);
            all.add(NATURE);
            all.add(MISC);
            all.add(NETHER);

        }

        return all;
    }

    public RoomGroup(String folder, int weight) {
        this.folder = folder;
        this.weight = weight;
    }

    public String folder;
    int weight;
    public boolean canBeMainTheme = true; // TODO

    public final boolean allowsOtherTypes() {
        return !this.possibleOtherTypes()
            .isEmpty();
    }

    public RoomGroup getFallbackGroup() {
        return MISC;
    }

    public abstract List<RoomGroup> possibleOtherTypes();

    @Override
    public int Weight() {
        return weight;
    }

}
