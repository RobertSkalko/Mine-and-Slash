package com.robertx22.mine_and_slash.config.dimension_configs;

import com.robertx22.mine_and_slash.config.IConfig;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;

import java.util.HashMap;

public class DimensionsContainer implements IConfig {

    public static DimensionsContainer INSTANCE = new DimensionsContainer();

    public DimensionsContainer() {

        if (dimensionsList.isEmpty()) {
            dimensionsList.put("minecraft:overworld", (DimensionConfig.Overworld()));
            dimensionsList.put("minecraft:the_end", (DimensionConfig.End()));
            dimensionsList.put("minecraft:the_nether", (DimensionConfig.Nether()));
        }
    }

    String version = "1.0";

    public String ConfigType = "dimensions_config";

    @Override
    public String GUID() {
        return ConfigType;
    }

    DimensionConfig defaultconfig = DimensionConfig.DefaultExtra();

    public HashMap<String, DimensionConfig> dimensionsList = new HashMap();

    public boolean hasConfig(World world) {
        String id = getId(world);

        return hasConfig(id);
    }

    private boolean hasConfig(String id) {
        if (dimensionsList.containsKey(id)) {
            return true;
        }

        return false;
    }

    private String getId(IWorld world) {

        String id = "";

        if (world != null && world.getDimension() != null) {

            ResourceLocation loc = DimensionType.getKey(world.getDimension().getType());

            if (loc != null) {
                id = loc.toString();
            }

        }
        return id;
    }

    public DimensionConfig getConfig(IWorld world) {
        String id = getId(world);

        if (dimensionsList.containsKey(id)) {

            return getConfig(id);
        }

        return defaultconfig; // default

    }

    private DimensionConfig getConfig(String id) {

        if (dimensionsList.containsKey(id)) {
            return this.dimensionsList.get(id);
        }

        return defaultconfig; // default
    }

}
