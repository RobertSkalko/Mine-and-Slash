package com.robertx22.mine_and_slash.database.world_providers;

import com.robertx22.mine_and_slash.database.map_affixes.beneficial.ele_dmg.BonusFireDamageAffix;
import com.robertx22.mine_and_slash.saveclasses.mapitem.MapAffixData;
import com.robertx22.mine_and_slash.world_gen.biome_color_schemes.bases.BiomeColorTheme;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

public class RockDesertIWP extends BaseWorldProvider {

    public RockDesertIWP(World world, DimensionType type) {
        super(world, type);
    }

    @Override
    public String GUID() {
        return "rock_desert";
    }

    @Override
    public BiomeColorTheme biomeTheme() {
        return BiomeColorTheme.RED_DESERT;
    }

    @Override
    public List<MapAffixData> getMapAffixes() {
        return Arrays.asList(new MapAffixData(new BonusFireDamageAffix(), 100));
    }

    @Override
    public Biome getBiome() {
        return Biomes.MODIFIED_BADLANDS_PLATEAU;
    }

    @Override
    public BiFunction<World, DimensionType, ? extends Dimension> classFactory() {
        return RockDesertIWP::new;
    }

    @Override
    public String locNameForLangFile() {
        return "Rock Desert";
    }
}