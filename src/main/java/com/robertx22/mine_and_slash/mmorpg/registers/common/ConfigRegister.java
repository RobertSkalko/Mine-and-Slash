package com.robertx22.mine_and_slash.mmorpg.registers.common;

import com.robertx22.mine_and_slash.config.ClientContainer;
import com.robertx22.mine_and_slash.config.ModConfig;
import com.robertx22.mine_and_slash.config.compatible_items.ConfigItemsSerialization;
import com.robertx22.mine_and_slash.config.dimension_configs.ConfigDimensionsSerialization;
import com.robertx22.mine_and_slash.config.mod_dmg_whitelist.ModDmgWhitelistSerialization;
import com.robertx22.mine_and_slash.config.whole_mod_entity_configs.ModEntityConfigsSerialization;
import com.robertx22.mine_and_slash.db_lists.registry.ISlashRegistryEntry;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistryContainer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig.Type;

import java.util.List;

public class ConfigRegister {

    public static void registerCustomConfigs() {

        unregisterFlaggedEntries(); // call first
        regConfigsWhichDontNeedMyRegistry();
        regConfigsWhichNeedMyRegistry();
        createTutorials();

    }

    private static void createTutorials() {

        ConfigItemsSerialization.INSTANCE.generateConfigTutorials();

    }

    private static void regConfigsWhichDontNeedMyRegistry() {

        ConfigDimensionsSerialization.INSTANCE.generateIfEmpty();
        ModDmgWhitelistSerialization.INSTANCE.generateIfEmpty();
        ModEntityConfigsSerialization.INSTANCE.generateIfEmpty();

        ConfigDimensionsSerialization.INSTANCE.load();
        ModDmgWhitelistSerialization.INSTANCE.load();
        ModEntityConfigsSerialization.INSTANCE.load();
    }

    private static void regConfigsWhichNeedMyRegistry() {

        ConfigItemsSerialization.INSTANCE.generateIfEmpty();
        ConfigItemsSerialization.INSTANCE.load();

    }

    private static void unregisterFlaggedEntries() {

        for (SlashRegistryContainer container : SlashRegistry.getAllRegistries()) {

            List<ISlashRegistryEntry> list = container.getList();

            for (ISlashRegistryEntry entry : list) {
                if (entry.unregisterBeforeConfigsLoad()) {
                    container.unRegister(entry);
                }
            }

        }

    }

    // MUST BE CALLED IN MAIN CLASS
    public static void registerForgeConfigs() {

        ModLoadingContext ctx = ModLoadingContext.get();

        DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
            ctx.registerConfig(Type.CLIENT, ClientContainer.spec, "MineAndSlash-Client.toml");
        });

        ctx.registerConfig(Type.SERVER, ModConfig.spec, "MineAndSlash-Server.toml");
    }

}
