package com.robertx22.mine_and_slash.mmorpg.registers.common;

import com.robertx22.mine_and_slash.config.base.ISerializedConfig;
import com.robertx22.mine_and_slash.config.base_player_stat.BasePlayerStatSerial;
import com.robertx22.mine_and_slash.config.compatible_items.CompatibleItemSerial;
import com.robertx22.mine_and_slash.config.dimension_configs.DimensionsSerial;
import com.robertx22.mine_and_slash.config.forge.ClientContainer;
import com.robertx22.mine_and_slash.config.forge.ModConfig;
import com.robertx22.mine_and_slash.config.lvl_penalty.LvlPenaltySerial;
import com.robertx22.mine_and_slash.config.mod_dmg_whitelist.ModDmgWhitelistSerial;
import com.robertx22.mine_and_slash.config.whole_mod_entity_configs.ModEntityConfigsSerial;
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
        DIMENSIONS,
        LVL_PENALTY,
        BASE_PLAYER_STATS
    }

    public static void registerCustomConfigs() {

        CONFIGS.put(Config.COMPATIBLE_ITEM, CompatibleItemSerial.INSTANCE);
        CONFIGS.put(Config.MOD_DMG_WHITELIST, ModDmgWhitelistSerial.INSTANCE);
        CONFIGS.put(Config.MOD_ENTITY, ModEntityConfigsSerial.INSTANCE);
        CONFIGS.put(Config.DIMENSIONS, DimensionsSerial.INSTANCE);
        CONFIGS.put(Config.BASE_PLAYER_STATS, BasePlayerStatSerial.INSTANCE);
        CONFIGS.put(Config.LVL_PENALTY, LvlPenaltySerial.INSTANCE);

        unregisterFlaggedEntries(); // call first

        generateIfEmpty();

        load();

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
