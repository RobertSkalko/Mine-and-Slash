package prospector.traverse.world.biomes;

import net.minecraft.block.BlockSand;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeDesert;
import prospector.traverse.config.TraverseConfig;
import prospector.traverse.world.WorldGenConstants;

public class BiomeRedDesert extends BiomeDesert implements WorldGenConstants {

    protected static final IBlockState RED_SAND = Blocks.SAND.getDefaultState().withProperty(BlockSand.VARIANT, BlockSand.EnumType.RED_SAND);
    public static BiomeProperties properties = new BiomeProperties("Red Desert");

    static {
        properties.setTemperature(Biomes.DESERT.getDefaultTemperature());
        properties.setRainfall(Biomes.DESERT.getRainfall());
        properties.setRainDisabled();
        properties.setBaseHeight(Biomes.DESERT.getBaseHeight());
        properties.setHeightVariation(Biomes.DESERT.getHeightVariation());
    }

    public BiomeRedDesert() {
        super(properties);
        this.topBlock = RED_SAND;
        this.fillerBlock = RED_SAND;
        decorator.generateFalls = false;
    }

    @Override
    public int getSkyColorByTemp(float currentTemperature) {
        if (TraverseConfig.disableCustomSkies)
            return super.getSkyColorByTemp(currentTemperature);
        else
            return 0xFFFFE5DD;
    }
}
