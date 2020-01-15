package com.robertx22.mine_and_slash.mmorpg.registers.common;

import com.robertx22.mine_and_slash.config.ClientContainer;
import com.robertx22.mine_and_slash.config.ModConfig;
import com.robertx22.mine_and_slash.config.base.ISerializedConfig;
import com.robertx22.mine_and_slash.config.serialization.CompatibleItemSerial;
import com.robertx22.mine_and_slash.config.serialization.DimensionsSerial;
import com.robertx22.mine_and_slash.config.serialization.ModDmgWhitelistSerial;
import com.robertx22.mine_and_slash.config.serialization.ModEntityConfigsSerial;
import com.robertx22.mine_and_slash.db_lists.registry.ISlashRegistryEntry;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistryContainer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig.Type;

import java.util.HashMap;
import java.util.List;

public class ConfigRegister {

    public static HashMap<Config, ISerializedConfig> CONFIGS = new HashMap<>();
    public static HashMap<Config, List<String>> SAVED_JSONS = new HashMap<>();

    public enum Config {
        COMPATIBLE_ITEM,
        MOD_DMG_WHITELIST,
        MOD_ENTITY,
        DIMENSIONS
    }

    public static void registerCustomConfigs() {

        CONFIGS.put(Config.COMPATIBLE_ITEM, CompatibleItemSerial.INSTANCE);
        CONFIGS.put(Config.MOD_DMG_WHITELIST, ModDmgWhitelistSerial.INSTANCE);
        CONFIGS.put(Config.MOD_ENTITY, ModEntityConfigsSerial.INSTANCE);
        CONFIGS.put(Config.DIMENSIONS, DimensionsSerial.INSTANCE);

        unregisterFlaggedEntries(); // call first

        generateIfEmpty();

        DistExecutor.runWhenOn(Dist.DEDICATED_SERVER, () -> () -> {
            load();
        });

        createTutorials();

    }

    private static void createTutorials() {
        CompatibleItemSerial.INSTANCE.generateConfigTutorials();
    }

    // should be called only on server, then packets sent to client
    private static void load() {
        CONFIGS.values().forEach(x -> x.loadOnServer());
    }

    private static void generateIfEmpty() {
        CONFIGS.values().forEach(x -> x.generateIfEmpty());
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
