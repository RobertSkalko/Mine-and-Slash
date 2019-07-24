package com.robertx22.mine_and_slash.database.world_providers;

import com.robertx22.mine_and_slash.database.map_affixes.beneficial.ele_dmg.BonusNatureDamageAffix;
import com.robertx22.mine_and_slash.database.map_affixes.beneficial.ele_dmg.BonusWaterDamageAffix;
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

public class SwampHillsIWP extends BaseWorldProvider {

    public SwampHillsIWP(World world, DimensionType type) {
        super(world, type);
    }

    @Override
    public String GUID() {
        return "swamp_hills";
    }

    @Override
    public BiomeColorTheme biomeTheme() {
        return BiomeColorTheme.GREEN;
    }

    @Override
    public List<MapAffixData> getMapAffixes() {
        return Arrays.asList(new MapAffixData(new BonusNatureDamageAffix(), 50), new MapAffixData(new BonusWaterDamageAffix(), 50));
    }

    @Override
    public Biome getBiome() {
        return Biomes.SWAMP_HILLS;
    }

    @Override
    public BiFunction<World, DimensionType, ? extends Dimension> classFactory() {
        return SwampHillsIWP::new;
    }

    @Override
    public String locNameForLangFile() {
        return "The Swamp";
    }
}