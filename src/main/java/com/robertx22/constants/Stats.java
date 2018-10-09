package com.robertx22.constants;

import java.util.ArrayList;
import java.util.List;

public class Stats {

    public static Stat CRIT_CHANCE = new Stat("CRIT_CHANCE", 1, 10, true, Tag.ALL_TYPES, 80);
    public static Stat CRIT_DAMAGE = new Stat("CRIT_DAMAGE", 3, 20, true, Tag.ALL_TYPES, 0);
    public static Stat DODGE = new Stat("DODGE", 1, 5, true, Tag.ARMOR, 50);
    public static Stat BLOCK = new Stat("BLOCK", 1, 10, true, Tag.ARMOR, 50);
    public static Stat LIFESTEAL = new Stat("LIFESTEAL", 1, 15, true, Tag.WEAPON, 20);
    public static Stat ARMOR = new Stat("ARMOR", 1, 10, true, Tag.ARMOR, 20);

    public static Stat FIRE_DMG = new Stat("FIRE_DMG", 1, 25, false, Tag.WEAPON, 0);
    public static Stat ICE_DMG = new Stat("ICE_DMG", 1, 25, false, Tag.WEAPON, 0);
    public static Stat STORM_DMG = new Stat("STORM_DMG", 1, 25, false, Tag.WEAPON, 0);
    public static Stat DARK_DMG = new Stat("DARK_DMG", 1, 25, false, Tag.WEAPON, 0);
    public static Stat LIGHT_DMG = new Stat("LIGHT_DMG", 1, 25, false, Tag.WEAPON, 0);

    public static Stat BONUS_FIRE_DMG = new Stat("BONUS_FIRE_DMG", 1, 10, true, Tag.ARMOR, 100);
    public static Stat BONUS_ICE_DMG = new Stat("BONUS_ICE_DMG", 1, 10, true, Tag.ARMOR, 100);
    public static Stat BONUS_STORM_DMG = new Stat("BONUS_STORM_DMG", 1, 10, true, Tag.ARMOR, 100);
    public static Stat BONUS_DARK_DMG = new Stat("BONUS_DARK_DMG", 1, 10, true, Tag.ARMOR, 100);
    public static Stat BONUS_LIGHT_DMG = new Stat("BONUS_LIGHT_DMG", 1, 10, true, Tag.ARMOR, 100);

    public static Stat FIRE_DMG_MULTI = new Stat("FIRE_DMG_MULTI", 1, 20, true, Tag.ARMOR, 0);
    public static Stat ICE_DMG_MULTI = new Stat("ICE_DMG_MULTI", 1, 20, true, Tag.ARMOR, 0);
    public static Stat STORM_DMG_MULTI = new Stat("STORM_DMG_MULTI", 1, 20, true, Tag.ARMOR, 0);
    public static Stat DARK_DMG_MULTI = new Stat("DARK_DMG_MULTI", 1, 20, true, Tag.ARMOR, 0);
    public static Stat LIGHT_DMG_MULTI = new Stat("LIGHT_DMG_MULTI", 1, 20, true, Tag.ARMOR, 0);

    public static Stat HEALTH = new Stat("HEALTH", 3, 50, false, Tag.ARMOR, 0);
    public static Stat MANA = new Stat("MANA", 2, 10, false, Tag.ARMOR, 0);
    public static Stat MANA_REGEN = new Stat("MANA_REGEN", 1, 6, true, Tag.ARMOR, 0);
    public static Stat HEALTH_REGEN = new Stat("HEALTH_REGEN", 1, 6, true, Tag.ARMOR, 0);
    public static Stat MIN_DAMAGE = new Stat("MIN_DAMAGE", 1, 25, false, Tag.WEAPON, 0);
    public static Stat MAX_DAMAGE = new Stat("MAX_DAMAGE", 1, 25, false, Tag.WEAPON, 0);
    public static Stat HEAL_POWER = new Stat("HEAL_POWER", 1, 10, true, Tag.ARMOR, 0);
    public static Stat ATTACK_POWER = new Stat("ATTACK_POWER", 1, 20, true, Tag.ARMOR, 0);
    public static Stat MAGIC_POWER = new Stat("MAGIC_POWER", 1, 10, true, Tag.ARMOR, 0);

    public static List<Stat> getAllRandomStats() {

        List<Stat> ALL_RANDOM_STATS = new ArrayList<Stat>();

        ALL_RANDOM_STATS = addRandomStatsToList(ALL_RANDOM_STATS);

        return ALL_RANDOM_STATS;

    }

    public static List<Stat> getAllStats() {

        List<Stat> ALL_STATS = new ArrayList<Stat>();

        ALL_STATS = addRandomStatsToList(ALL_STATS);

        ALL_STATS.add(HEALTH);
        ALL_STATS.add(MIN_DAMAGE);
        ALL_STATS.add(MAX_DAMAGE);

        return ALL_STATS;
    }

    public static List<Stat> addRandomStatsToList(List<Stat> ALL_RANDOM_STATS) {

        ALL_RANDOM_STATS.add(ARMOR);
        ALL_RANDOM_STATS.add(CRIT_CHANCE);
        ALL_RANDOM_STATS.add(CRIT_DAMAGE);
        ALL_RANDOM_STATS.add(DODGE);
        ALL_RANDOM_STATS.add(BLOCK);
        ALL_RANDOM_STATS.add(HEAL_POWER);
        ALL_RANDOM_STATS.add(ATTACK_POWER);
        ALL_RANDOM_STATS.add(MAGIC_POWER);
        ALL_RANDOM_STATS.add(LIFESTEAL);
        ALL_RANDOM_STATS.add(MANA);
        ALL_RANDOM_STATS.add(MANA_REGEN);
        ALL_RANDOM_STATS.add(HEALTH_REGEN);

        ALL_RANDOM_STATS.add(FIRE_DMG);
        ALL_RANDOM_STATS.add(ICE_DMG);
        ALL_RANDOM_STATS.add(STORM_DMG);
        ALL_RANDOM_STATS.add(DARK_DMG);
        ALL_RANDOM_STATS.add(LIGHT_DMG);

        ALL_RANDOM_STATS.add(BONUS_FIRE_DMG);
        ALL_RANDOM_STATS.add(BONUS_ICE_DMG);
        ALL_RANDOM_STATS.add(BONUS_STORM_DMG);
        ALL_RANDOM_STATS.add(BONUS_DARK_DMG);
        ALL_RANDOM_STATS.add(BONUS_LIGHT_DMG);

        ALL_RANDOM_STATS.add(FIRE_DMG_MULTI);
        ALL_RANDOM_STATS.add(ICE_DMG_MULTI);
        ALL_RANDOM_STATS.add(STORM_DMG_MULTI);
        ALL_RANDOM_STATS.add(DARK_DMG_MULTI);
        ALL_RANDOM_STATS.add(LIGHT_DMG_MULTI);

        return ALL_RANDOM_STATS;

    }

}
