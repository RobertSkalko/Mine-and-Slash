package com.robertx22.mine_and_slash.config;

import com.robertx22.mine_and_slash.config.base.EntityConfig;
import net.minecraftforge.common.ForgeConfigSpec;

public class EntityConfigs {

    public EntityConfig NPC_CONFIG;
    public EntityConfig MOB_CONFIG;
    public EntityConfig ANIMAL_CONFIG;
    public EntityConfig OTHER_CONFIG;

    public EntityConfigs(ForgeConfigSpec.Builder builder) {
        builder.push("ENTITIES");

        NPC_CONFIG = builder.configure((ForgeConfigSpec.Builder builder1) -> new EntityConfig("NPC", builder1, 0.3D, 0.3D))
                .getLeft();

        MOB_CONFIG = builder.configure((ForgeConfigSpec.Builder builder1) -> new EntityConfig("MOB", builder1, 1D, 1D))
                .getLeft();

        ANIMAL_CONFIG = builder.configure((ForgeConfigSpec.Builder builder1) -> new EntityConfig("ANIMAL", builder1, 0.01D, 0.1D))
                .getLeft();

        OTHER_CONFIG = builder.configure((ForgeConfigSpec.Builder builder1) -> new EntityConfig("OTHER", builder1, 0D, 0D))
                .getLeft();

        builder.pop();

    }

}
