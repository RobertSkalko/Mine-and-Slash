package com.robertx22.mine_and_slash.config.dimension_configs;

import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.db_lists.registry.ISlashRegistryEntry;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistryType;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Objects;

public class DimensionConfig implements ISlashRegistryEntry<DimensionConfig> {

    public DimensionConfig() {

    }

    public static DimensionConfig Overworld() {
        return new DimensionConfig(1, 100);
    }

    public static DimensionConfig Nether() {
        return new DimensionConfig(75, 100, 10, 100);
    }

    public static DimensionConfig End() {
        return new DimensionConfig(25, 100);
    }

    public static DimensionConfig DefaultExtra() {
        return new DimensionConfig(1, 100);
    }

    @Override
    public boolean unregisterBeforeConfigsLoad() {
        return true;
    }

    public DimensionConfig(int min, int max) {
        this.MINIMUM_MOB_LEVEL = min;
        this.MAXIMUM_MOB_LEVEL = max;
    }

    public DimensionConfig(int distance, int area, int min, int max) {
        this.MOB_LEVEL_PER_DISTANCE = distance;
        this.MOB_LEVEL_ONE_AREA = area;
        this.MINIMUM_MOB_LEVEL = min;
        this.MAXIMUM_MOB_LEVEL = max;

    }

    static class Pos {

        public int x, z, y;

        public Pos(int x, int z, int y) {
            this.x = x;
            this.z = z;
            this.y = y;
        }

        public BlockPos getPos() {
            return new BlockPos(x, y, z);
        }
    }

    public boolean USE_SPECIFIC_LVL_1_POSITION = false;

    public Pos SPECIFIC_LVL_1_POSITION = new Pos(0, 0, 0);

    public transient String GUID = "";

    public int MOB_LEVEL_PER_DISTANCE = 125;

    public int MOB_LEVEL_ONE_AREA = 50;

    public int MAXIMUM_MOB_LEVEL = 100;

    public int MINIMUM_MOB_LEVEL = 1;

    public int LEVEL_FOR_MOBS_TO_BE_LEGENDARY = 10;

    public int LEVEL_FOR_MOBS_TO_BE_MYTHICAL = 20;

    public boolean SCALE_MOB_LEVEL_TO_NEAREST_PLAYER = false;

    public boolean DROPS_UNIQUE_ITEMS = false;

    public int MAP_TIER = 0;

    public float DROP_MULTIPLIER = 1F;

    public float MOB_STRENGTH_MULTIPLIER = 1F;

    @Override
    public SlashRegistryType getSlashRegistryType() {
        return SlashRegistryType.DIMENSION_CONFIGS;
    }

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public int Weight() {
        return 1;
    }

    @Override
    public int getRarityRank() {
        return 0;
    }

    @Override
    public Rarity getRarity() {
        return Rarities.Gears.get(getRarityRank());
    }

    @Override
    public int Tier() {
        return this.MAP_TIER;
    }

    public boolean isMapWorld() {
        return MAP_TIER > 0;
    }

    public BlockPos getSpawnPos(World world) {

        BlockPos pos = null;

        if (USE_SPECIFIC_LVL_1_POSITION) {
            pos = SPECIFIC_LVL_1_POSITION.getPos();
        } else {
            pos = world.getSpawnPoint();
        }

        Objects.requireNonNull(pos, "Spawn pos cant be null, setting to 0,0,0!");

        pos = new BlockPos(0, 0, 0);

        return pos;

    }
}
