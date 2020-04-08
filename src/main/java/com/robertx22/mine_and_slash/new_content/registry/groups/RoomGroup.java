package com.robertx22.mine_and_slash.new_content.registry.groups;

import com.robertx22.mine_and_slash.new_content.data_processors.bases.SpawnedMob;
import com.robertx22.mine_and_slash.new_content.enums.RoomType;
import com.robertx22.mine_and_slash.uncommon.interfaces.IWeighted;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
    public static SewersGroup SEWERS = new SewersGroup();
    public static WideNatureRoom WIDE_NATURE = new WideNatureRoom();

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
            all.add(SEWERS);
            all.add(WIDE_NATURE);
        }

        return all;
    }

    public RoomGroup(String folder, int weight) {
        this.folder = folder;
        this.weight = weight;
    }

    public String folder;
    int weight;
    public boolean canBeMainTheme = true;
    public boolean canSpawnFireMobs = false;
    public int chanceForOtherGroups = 20;

    public final boolean hasRoomFor(RoomType type) {
        return type.getAllOfThisTypeRooms()
            .stream()
            .anyMatch(x -> x.group.equals(this));

    }

    public final boolean allowsOtherTypes() {
        return !this.possibleOtherTypes()
            .isEmpty();
    }

    public boolean canSpawnMob(SpawnedMob mob) {
        if (mob.isFire && !canSpawnFireMobs) {
            return false;
        }

        return true;
    }

    public RoomGroup getFallbackGroup(Random rand) {
        if (possibleOtherTypes().isEmpty()) {
            return MISC;
        } else {
            return RandomUtils.weightedRandom(possibleOtherTypes(), rand.nextDouble());
        }
    }

    public abstract List<RoomGroup> possibleOtherTypes();

    @Override
    public int Weight() {
        return weight;
    }

}
