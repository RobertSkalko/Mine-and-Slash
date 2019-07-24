package com.robertx22.mine_and_slash.database.world_providers;

import com.robertx22.mine_and_slash.db_lists.bases.IBonusLootMulti;
import com.robertx22.mine_and_slash.saveclasses.mapitem.MapAffixData;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAutoLocName;
import com.robertx22.mine_and_slash.uncommon.interfaces.IWeighted;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import com.robertx22.mine_and_slash.world_gen.biome_color_schemes.bases.BiomeColorTheme;
import com.robertx22.mine_and_slash.world_gen.types.FeatureType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.ModDimension;

import java.util.List;

public interface IWP extends IWeighted, IAutoLocName, IBonusLootMulti {
    abstract String GUID();

    ModDimension getModDim();

    ResourceLocation getResourceLoc();

    BiomeColorTheme biomeTheme();

    ModDimension newModDimension();

    List<MapAffixData> getMapAffixes(); // missing thunder damage maps.. hmm

    void setModDimension(ModDimension mod);

    @Override
    public default AutoLocGroup locNameGroup() {
        return AutoLocGroup.World_Types;
    }

    Biome getBiome();

    List<FeatureType> smallSurfaceDecorations();

    List<FeatureType> smallTreasures();

    default FeatureType randomSmallTreasure() {
        if (smallTreasures().isEmpty()) {
            return null;
        }

        return RandomUtils.weightedRandom(smallTreasures());
    }

    default FeatureType randomSmallSurfaceDecoration() {
        if (smallSurfaceDecorations().isEmpty()) {
            return null;
        }
        return RandomUtils.weightedRandom(smallSurfaceDecorations());
    }

}
