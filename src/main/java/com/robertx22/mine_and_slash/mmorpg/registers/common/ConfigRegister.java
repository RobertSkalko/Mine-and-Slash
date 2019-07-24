package com.robertx22.mine_and_slash.mmorpg.registers.common;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import com.robertx22.mine_and_slash.config.ClientContainer;
import com.robertx22.mine_and_slash.config.ModConfig;
import com.robertx22.mine_and_slash.config.compatible_items.ConfigItemsSerialization;
import com.robertx22.mine_and_slash.config.dimension_configs.ConfigDimensionsSerialization;
import com.robertx22.mine_and_slash.config.mod_dmg_whitelist.ModDmgWhitelistSerialization;
import com.robertx22.mine_and_slash.config.whole_mod_entity_configs.ModEntityConfigsSerialization;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig.Type;
import net.minecraftforge.fml.loading.FMLPaths;

import java.nio.file.Path;

public class ConfigRegister {

    public static void regAndLoadNonForgeConfigs() {

        ConfigItemsSerialization.INSTANCE.generateIfEmpty();
        ConfigDimensionsSerialization.INSTANCE.generateIfEmpty();
        ModDmgWhitelistSerialization.INSTANCE.generateIfEmpty();
        ModEntityConfigsSerialization.INSTANCE.generateIfEmpty();

        ConfigItemsSerialization.INSTANCE.load();
        ConfigDimensionsSerialization.INSTANCE.load();
        ModDmgWhitelistSerialization.INSTANCE.load();
        ModEntityConfigsSerialization.INSTANCE.load();

    }

    // MUST BE CALLED IN MAIN CLASS
    public static void register() {

        ModLoadingContext ctx = ModLoadingContext.get();

        DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
            ctx.registerConfig(Type.CLIENT, ClientContainer.spec);
        });

        ctx.registerConfig(Type.COMMON, ModConfig.spec);

    }

    // MUST BE CALLED IN MAIN CLASS
    public static void load() {

        DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
            loadConfig(ClientContainer.spec, FMLPaths.CONFIGDIR.get()
                    .resolve(Ref.MODID + "-" + "client" + ".toml")); // needs to be modid
        });

        loadConfig(ModConfig.spec, FMLPaths.CONFIGDIR.get()
                .resolve(Ref.MODID + "-" + "common" + ".toml"));

    }

    // MUST BE CALLED IN MAIN CLASS
    private static void loadConfig(ForgeConfigSpec spec, Path path) {

        final CommentedFileConfig configData = CommentedFileConfig.builder(path)
                .sync()
                .autosave()
                .writingMode(WritingMode.REPLACE)
                .build();

        configData.load();

        spec.setConfig(configData);
    }
}
