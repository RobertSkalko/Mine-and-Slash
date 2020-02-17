package com.robertx22.mine_and_slash.config.whole_mod_entity_configs;

import com.robertx22.mine_and_slash.registry.ISlashRegistryInit;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.EntityTypeUtils;

import java.util.HashMap;

public class ModEntityConfigs implements ISlashRegistryInit {

    public ModEntityConfigs() {
        specificMobs.put("mob_id", new ModEntityConfig());
        allMobsInAMod.put("modid", new ModEntityConfig());

        byEntityTypeDefault.put(EntityTypeUtils.EntityType.MOB, new ModEntityConfig(1, 1));
        byEntityTypeDefault.put(EntityTypeUtils.EntityType.ANIMAL, new ModEntityConfig(0.01F, 0.1F));
        byEntityTypeDefault.put(EntityTypeUtils.EntityType.NPC, new ModEntityConfig(0.3F, 0.3F));
        byEntityTypeDefault.put(EntityTypeUtils.EntityType.OTHER, new ModEntityConfig(0, 0));
        byEntityTypeDefault.put(EntityTypeUtils.EntityType.PLAYER, new ModEntityConfig(0, 0));

    }

    public HashMap<String, ModEntityConfig> specificMobs = new HashMap<>();

    public HashMap<EntityTypeUtils.EntityType, ModEntityConfig> byEntityTypeDefault = new HashMap<>();

    public HashMap<String, ModEntityConfig> allMobsInAMod = new HashMap<>();

    @Override
    public void registerAll() {
        specificMobs.entrySet().forEach(x -> x.getValue().GUID = x.getKey());
        allMobsInAMod.entrySet().forEach(x -> x.getValue().GUID = x.getKey());
        specificMobs.entrySet().forEach(x -> x.getValue().registerToSlashRegistry());
        allMobsInAMod.entrySet().forEach(x -> x.getValue().registerToSlashRegistry());

        SlashRegistry.EntityConfigs().byEntityTypeDefault = this.byEntityTypeDefault;

    }
}
