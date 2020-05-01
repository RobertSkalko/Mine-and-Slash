package com.robertx22.mine_and_slash.database.world_providers;

import com.robertx22.mine_and_slash.database.world_providers.base.BaseDungeonDimension;
import com.robertx22.mine_and_slash.database.world_providers.base.MyWorldInfo;
import com.robertx22.mine_and_slash.new_content.building.DungeonUtils;
import com.robertx22.mine_and_slash.new_content.dimension.UniqueDungeonBiomeProvider;
import com.robertx22.mine_and_slash.saveclasses.mapitem.MapAffixData;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

public class UniqueDungeonDimension extends BaseDungeonDimension {

    public UniqueDungeonDimension(World world, DimensionType type) {
        super(world, type);
    }

    @Override
    public BiomeProvider getBiomeProvider() {
        return new UniqueDungeonBiomeProvider();
    }

    @Override
    public String GUID() {
        return "unique_dungeon";
    }

    @Override
    public List<MapAffixData> getMapAffixes() {
        return Arrays.asList();
    }

    @Override
    public BiFunction<World, DimensionType, ? extends Dimension> classFactory() {
        return (world1, type) -> {
            UniqueDungeonDimension dim = new UniqueDungeonDimension(world1, type);
            world1.worldInfo = new MyWorldInfo(world1.worldInfo);
            return dim;

        };
    }

    public BlockPos getEntrancePos(ChunkPos cpos) {
        return DungeonUtils.getDungeonStartTeleportPos(cpos); // todo make it be based on unique dungeon on the position.
    }

    @Override
    public String locNameForLangFile() {
        return "Unique Dungeon Dimension";
    }

}
