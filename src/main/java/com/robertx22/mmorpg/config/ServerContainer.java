package com.robertx22.mmorpg.config;

import com.robertx22.uncommon.enumclasses.EntitySystemChoice;

import net.minecraftforge.common.config.Config;

public class ServerContainer {

    @Config.Name("Enable Outside Item Stats")
    @Config.LangKey("mmorpg.config.")
    @Config.Comment("This enables adding stats to items not from my mod automatically if they are added to my mod's config file inside the folder")
    public boolean USE_COMPATIBILITY_ITEMS = false;

    @Config.Name("Disable Vanilla Hp Regen")
    @Config.LangKey("mmorpg.config.")
    @Config.Comment("This should always be disabled because otherwise with my mod's nerfed vanilla healing, the hp regen spends way too much hunger. Also you have hp regen from my mod")
    public boolean DISABLE_VANILLA_HP_REGEN = true;

    @Config.Name("Maximum Worn Runed Items")
    @Config.LangKey("mmorpg.config.")
    @Config.Comment("")
    public int MAXIMUM_WORN_RUNED_ITEMS = 3;

    @Config.Name("Maximum Worn Unique Items")
    @Config.LangKey("mmorpg.config.")
    @Config.Comment("")
    public int MAXIMUM_WORN_UNIQUE_ITEMS = 3;

    @Config.Name("Generate Ores")
    @Config.LangKey("mmorpg.config.")
    @Config.Comment("")
    public boolean GENERATE_ORES = true;

    @Config.Name("Unarmed Energy Cost")
    @Config.LangKey("mmorpg.config.unarmed_energy_cost")
    @Config.Comment("If an item isn't a weapon, it does unarmed damage. Meaning your base damage")
    public float UNARMED_ENERGY_COST = 1;

    @Config.Name("Maps Start Dropping At Level")
    @Config.LangKey("mmorpg.config.maps_start_drop")
    @Config.Comment("If you don't want newbies to have their inventory spammed with maps before they even craft a map bag :3.")
    public int MAPS_DROP_AFTER_LEVEL = 20;

    @Config.Name("Currency Start Dropping At Level")
    @Config.LangKey("mmorpg.config.currency_start_drop")
    @Config.Comment("If you don't want newbies to have their inventory spammed with currency before they even craft a modify station. This Includes runeword awakening items")
    public int CURRENCY_DROP_AFTER_LEVEL = 10;

    @Config.Name("Player Level Cap")
    @Config.LangKey("mmorpg.config.player_level_cap")
    @Config.Comment("Select maximum level")
    public int MAXIMUM_PLAYER_LEVEL = 100;

    @Config.Name("Non Mod Damage Multiplier")
    @Config.LangKey("mmorpg.config.non_mod_damage_multiplier")
    @Config.Comment("0 to 1. 0 means other types of damage (not from my mod) are nullified. 1 means they are the same. Please leave at default value unless required to change it. Too high value means you do 2 different types of damage, vanilla damage (sword that does half a mob's health) plus my mod's damage.. Which will lead to you both one shotting mobs and mobs one shotting you too.")
    public float NON_MOD_DAMAGE_MULTI = 0.03F;

    @Config.Name("Mob Environment Damage Multiplier")
    @Config.LangKey("mmorpg.config.non_mod_damage_multiplier")
    @Config.Comment("")
    public float MOB_ENVIRONMENT_DAMAGE_MULTI = 0.2F;

    @Config.Name("Non Mod Heal Multiplier")
    @Config.LangKey("mmorpg.config.non_mod_heal_multi")
    @Config.Comment("0 to 1. 0 means other types of healing (not from my mod) are smaller. 1 means they are the same. Please leave at default value unless required to change it. High values make health regen too fast(both normal and my mods) but low value makes stuff like health potions useless. ")
    public float NON_MOD_HEAL_MULTI = 0.1F;

    @Config.Name("Exp multiplier")
    @Config.LangKey("mmorpg.config.exp_multiplier")
    @Config.Comment("Want to level faster or slower? 1 is normal, 0.5 half speed, 2 double")
    public float EXPERIENCE_MULTIPLIER = 1F;

    @Config.Name("Level Ups Cost Tokens")
    @Config.LangKey("mmorpg.config.levelup_cost_token")
    @Config.Comment("If false, player levels up automatically, if true, you need to craft a token and use it. Basically hardcore mode, every level needs diamonds.")
    public boolean LEVEL_UPS_COST_TOKEN = false;

    @Config.Name("Max Players Per Map")
    @Config.LangKey("mmorpg.config.max_players_in_map")
    @Config.Comment("")
    public int MAX_PLAYERS_PER_MAP = 5;

    @Config.Name("Entities Under System")
    @Config.LangKey("mmorpg.config.entities_under_system")
    @Config.Comment("By system, it means they get my mod's health, damage stats etc. They can also use the gear (if possible). All entities means even sheep or villagers are under the system. ")
    public EntitySystemChoice ENTITIES_UNDER_SYSTEM = EntitySystemChoice.All_Entities;

}
