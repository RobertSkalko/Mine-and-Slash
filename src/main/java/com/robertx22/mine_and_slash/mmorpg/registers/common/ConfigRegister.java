package com.robertx22.mine_and_slash.mmorpg.registers.common;

import com.robertx22.mine_and_slash.config.ClientContainer;
import com.robertx22.mine_and_slash.config.ModConfig;
import com.robertx22.mine_and_slash.config.compatible_items.ConfigItemsSerialization;
import com.robertx22.mine_and_slash.config.dimension_configs.ConfigDimensionsSerialization;
import com.robertx22.mine_and_slash.config.mod_dmg_whitelist.ModDmgWhitelistSerialization;
import com.robertx22.mine_and_slash.config.whole_mod_entity_configs.ModEntityConfigsSerialization;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig.Type;

public class ConfigRegister {

    public static void regConfigsWhichDontNeedMyRegistry() {

        ConfigDimensionsSerialization.INSTANCE.generateIfEmpty();
        ModDmgWhitelistSerialization.INSTANCE.generateIfEmpty();
        ModEntityConfigsSerialization.INSTANCE.generateIfEmpty();

        ConfigDimensionsSerialization.INSTANCE.load();
        ModDmgWhitelistSerialization.INSTANCE.load();
        ModEntityConfigsSerialization.INSTANCE.load();
    }

    public static void regConfigsWhichNeedMyRegistry() {

        ConfigItemsSerialization.INSTANCE.generateIfEmpty();
        ConfigItemsSerialization.INSTANCE.load();

    }

    // MUST BE CALLED IN MAIN CLASS
    public static void register() {

        ModLoadingContext ctx = ModLoadingContext.get();

        DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
            ctx.registerConfig(Type.CLIENT, ClientContainer.spec, "MineAndSlash-Client.toml");
        });

        ctx.registerConfig(Type.SERVER, ModConfig.spec, "MineAndSlash-Server.toml");
    }

}
