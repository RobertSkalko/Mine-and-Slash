package com.robertx22.mine_and_slash.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.DoubleValue;

public class StatConfig {

    public static final String NAME = "BASE_STATS";

    public DoubleValue physical_damage;
    public DoubleValue physical_damage_per_level;
    public DoubleValue mana;
    public DoubleValue mana_per_level;
    public DoubleValue energy;
    public DoubleValue energy_per_level;
    public DoubleValue mana_regen;
    public DoubleValue mana_regen_per_level;
    public DoubleValue health_regen;
    public DoubleValue health_regen_per_level;
    public DoubleValue armor;
    public DoubleValue armor_per_level;
    public DoubleValue health;
    public DoubleValue health_per_level;
    public DoubleValue critical_hit;
    public DoubleValue critical_hit_per_level;
    public DoubleValue energy_regen;
    public DoubleValue energy_regen_per_level;
    public DoubleValue critical_damage;
    public DoubleValue critical_damage_per_level;
    public DoubleValue spell_damage;
    public DoubleValue spell_damage_per_level;

    StatConfig(ForgeConfigSpec.Builder builder) {
        builder.push("PLAYER_BASE_STATS");

        physical_damage = builder.translation("mmorpg.stat.physical_damage")
                .defineInRange("physical_damage", 3F, 0, Integer.MAX_VALUE);
        physical_damage_per_level = builder.translation("mmorpg.stat.physical_damage_per_level")
                .defineInRange("physical_damage_per_level", 0.3F, 0, Integer.MAX_VALUE);
        mana = builder.translation("mmorpg.stat.mana")
                .defineInRange("mana", 75F, 0, Integer.MAX_VALUE);
        mana_per_level = builder.translation("mmorpg.stat.mana_per_level")
                .defineInRange("mana_per_level", 0F, 0, Integer.MAX_VALUE);
        energy = builder.translation("mmorpg.stat.energy")
                .defineInRange("energy", 150F, 0, Integer.MAX_VALUE);
        energy_per_level = builder.translation("mmorpg.stat.energy_per_level")
                .defineInRange("energy_per_level", 0F, 0, Integer.MAX_VALUE);
        mana_regen = builder.translation("mmorpg.stat.mana_regen")
                .defineInRange("mana_regen", 3F, 0, Integer.MAX_VALUE);
        mana_regen_per_level = builder.translation("mmorpg.stat.mana_regen_per_level")
                .defineInRange("mana_regen_per_level", 0F, 0, Integer.MAX_VALUE);
        health_regen = builder.translation("mmorpg.stat.health_regen")
                .defineInRange("health_regen", 5F, 0, Integer.MAX_VALUE);
        health_regen_per_level = builder.translation("mmorpg.stat.health_regen_per_level")
                .defineInRange("health_regen_per_level", 1F, 0, Integer.MAX_VALUE);
        armor = builder.translation("mmorpg.stat.armor")
                .defineInRange("armor", 10F, 0, Integer.MAX_VALUE);
        armor_per_level = builder.translation("mmorpg.stat.armor_per_level")
                .defineInRange("armor_per_level", 5F, 0, Integer.MAX_VALUE);
        health = builder.translation("mmorpg.stat.health")
                .defineInRange("health", 100F, 0, Integer.MAX_VALUE);
        health_per_level = builder.translation("mmorpg.stat.health_per_level")
                .defineInRange("health_per_level", 5F, 0, Integer.MAX_VALUE);
        critical_hit = builder.translation("mmorpg.stat.critical_hit")
                .defineInRange("critical_hit", 1F, 0, Integer.MAX_VALUE);
        critical_hit_per_level = builder.translation("mmorpg.stat.critical_hit_per_level")
                .defineInRange("critical_hit_per_level", 0F, 0, Integer.MAX_VALUE);
        energy_regen = builder.translation("mmorpg.stat.energy_regen")
                .defineInRange("energy_regen", 7F, 0, Integer.MAX_VALUE);
        energy_regen_per_level = builder.translation("mmorpg.stat.energy_regen_per_level")
                .defineInRange("energy_regen_per_level", 0F, 0, Integer.MAX_VALUE);
        critical_damage = builder.translation("mmorpg.stat.critical_damage")
                .defineInRange("critical_damage", 0F, 0, Integer.MAX_VALUE);
        critical_damage_per_level = builder.translation("mmorpg.stat.critical_damage_per_level")
                .defineInRange("critical_damage_per_level", 0F, 0, Integer.MAX_VALUE);
        spell_damage = builder.translation("mmorpg.stat.spell_damage")
                .defineInRange("spell_damage", 3F, 0, Integer.MAX_VALUE);
        spell_damage_per_level = builder.translation("mmorpg.stat.spell_damage_per_level")
                .defineInRange("spell_damage_per_level", 0.25F, 0, Integer.MAX_VALUE);

        builder.pop();

    }
}