package com.robertx22.mine_and_slash.registry;

import com.robertx22.mine_and_slash.config.whole_mod_entity_configs.ModEntityConfig;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.EntityTypeUtils;

import java.util.HashMap;

public class ModEntityContainer extends SlashRegistryContainer<ModEntityConfig> {

    public ModEntityContainer(SlashRegistryType type) {
        super(type, new ModEntityConfig(0));
    }

    public HashMap<EntityTypeUtils.EntityType, ModEntityConfig> byEntityTypeDefault = new HashMap<>();

}