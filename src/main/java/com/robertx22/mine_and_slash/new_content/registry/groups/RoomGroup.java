package com.robertx22.mine_and_slash.new_content.registry.groups;

import com.robertx22.mine_and_slash.new_content.enums.RoomType;
import com.robertx22.mine_and_slash.uncommon.interfaces.IWeighted;

import java.util.ArrayList;
import java.util.List;

public abstract class RoomGroup implements IWeighted {

    public static TestGroup TEST = new TestGroup();

    public static MossyBrickGroup MOSSY_BRICK = new MossyBrickGroup();
    public static StoneBrickGroup STONE_BRICK = new StoneBrickGroup();
    public static SandstoneGroup SANDSTONE = new SandstoneGroup();
    public static SpruceMansionGroup SPRUCE_MANSION = new SpruceMansionGroup();
    public static MineGroup MINESHAFT = new MineGroup();
    public static BrickGroup BRICK = new BrickGroup();
    public static TentGroup TENT = new TentGroup();
    public static SteampunkGroup STEAMPUNK = new SteampunkGroup();
    public static NatureGroup NATURE = new NatureGroup();
    public static MiscGroup MISC = new MiscGroup();
    public static NetherGroup NETHER = new NetherGroup();

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

    public final boolean hasRoomFor(RoomType type) {
        return type.getAllOfThisTypeRooms()
            .stream()
            .anyMatch(x -> x.group.equals(this));

    }

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
