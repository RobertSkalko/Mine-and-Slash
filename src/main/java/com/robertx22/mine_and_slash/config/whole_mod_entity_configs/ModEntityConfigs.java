package com.robertx22.mine_and_slash.config.whole_mod_entity_configs;

import com.robertx22.mine_and_slash.db_lists.registry.ISlashRegistryInit;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistryType;

import java.util.HashMap;

public class ModEntityConfigs implements ISlashRegistryInit {

    public static ModEntityConfigs INSTANCE = new ModEntityConfigs();

    public ModEntityConfigs() {
        specificMobs.put("mob_id", new ModEntityConfig());
        allMobsInAMod.put("modid", new ModEntityConfig());

    }

    private ModEntityConfig defaultConfig = new ModEntityConfig();

    public HashMap<String, ModEntityConfig> specificMobs = new HashMap<>();

    public HashMap<String, ModEntityConfig> allMobsInAMod = new HashMap<>();

    @Override
    public void registerAll() {
        specificMobs.entrySet().forEach(x -> x.getValue().GUID = x.getKey());
        allMobsInAMod.entrySet().forEach(x -> x.getValue().GUID = x.getKey());
        specificMobs.entrySet().forEach(x -> x.getValue().registerToSlashRegistry());
        allMobsInAMod.entrySet().forEach(x -> x.getValue().registerToSlashRegistry());
        SlashRegistry.getRegistry(SlashRegistryType.MOD_ENTITY_CONFIGS)
                .setDefault(defaultConfig);
    }
}
