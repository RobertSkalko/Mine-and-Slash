package com.robertx22.mine_and_slash.database.world_providers;

import com.robertx22.mine_and_slash.saveclasses.mapitem.MapAffixData;
import net.minecraft.world.World;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

public class DungeonDimension extends BaseDungeonDimension {

    public DungeonDimension(World world, DimensionType type) {
        super(world, type);
    }

    @Override
    public String GUID() {
        return "dungeon";
    }

    @Override
    public List<MapAffixData> getMapAffixes() {
        return Arrays.asList();
    }

    @Override
    public BiFunction<World, DimensionType, ? extends Dimension> classFactory() {
        return DungeonDimension::new;
    }

    @Override
    public String locNameForLangFile() {
        return "Dungeon Dimension";
    }

}