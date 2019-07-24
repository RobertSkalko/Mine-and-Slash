package com.robertx22.mine_and_slash.config.whole_mod_entity_configs;

import net.minecraft.entity.Entity;

import java.util.HashMap;

public class ModEntityConfigs {

    public static ModEntityConfigs INSTANCE = new ModEntityConfigs();

    public ModEntityConfigs() {
        specificMobs.put("mob_id", new ModEntityConfig());
        allMobsInAMod.put("modid", new ModEntityConfig());

    }

    private ModEntityConfig defaultConfig = new ModEntityConfig();

    public ModEntityConfig getConfig(Entity entity) {

        String monster_id = entity.getType().getRegistryName().toString();
        String mod_id = entity.getType().getRegistryName().getNamespace();

        if (specificMobs.containsKey(monster_id)) {
            return specificMobs.get(monster_id);
        } else {
            return allMobsInAMod.getOrDefault(mod_id, defaultConfig);
        }

    }

    public HashMap<String, ModEntityConfig> specificMobs = new HashMap<>();

    public HashMap<String, ModEntityConfig> allMobsInAMod = new HashMap<>();

}
