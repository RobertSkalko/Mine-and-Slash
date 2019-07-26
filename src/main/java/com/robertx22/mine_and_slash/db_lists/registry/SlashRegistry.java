package com.robertx22.mine_and_slash.db_lists.registry;

import com.robertx22.mine_and_slash.config.compatible_items.ConfigItem;
import com.robertx22.mine_and_slash.config.dimension_configs.DimensionConfig;
import com.robertx22.mine_and_slash.config.whole_mod_entity_configs.ModEntityConfig;
import com.robertx22.mine_and_slash.database.affixes.Prefix;
import com.robertx22.mine_and_slash.database.affixes.Suffix;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.item_modifications.bases.BaseItemModification;
import com.robertx22.mine_and_slash.database.item_modifications.gear_items.AddChaosStatMod;
import com.robertx22.mine_and_slash.database.map_affixes.BaseMapAffix;
import com.robertx22.mine_and_slash.database.runewords.RuneWord;
import com.robertx22.mine_and_slash.database.sets.Set;
import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.status_effects.bases.BaseStatusEffect;
import com.robertx22.mine_and_slash.database.unique_items.IUnique;
import com.robertx22.mine_and_slash.database.world_providers.BaseWorldProvider;
import com.robertx22.mine_and_slash.database.world_providers.BirchForestIWP;
import com.robertx22.mine_and_slash.db_lists.initializers.*;
import com.robertx22.mine_and_slash.db_lists.registry.empty_entries.*;
import com.robertx22.mine_and_slash.dimensions.MapManager;
import com.robertx22.mine_and_slash.items.runes.base.BaseRuneItem;
import com.robertx22.mine_and_slash.spells.bases.BaseSpell;
import net.minecraft.entity.Entity;
import net.minecraft.world.IWorld;

import java.util.HashMap;

public class SlashRegistry {

    public static DimensionConfig getDimensionConfig(IWorld world) {
        return DimensionConfigs().get(MapManager.getId(world));
    }

    public static ModEntityConfig getEntityConfig(Entity entity) {

        String monster_id = entity.getType().getRegistryName().toString();
        String mod_id = entity.getType().getRegistryName().getNamespace();

        if (ModEntityConfigs().isRegistered(monster_id)) {
            return ModEntityConfigs().get(monster_id);
        } else {
            if (ModEntityConfigs().isRegistered(mod_id)) {
                return ModEntityConfigs().get(mod_id);
            } else {
                return ModEntityConfigs().getDefault();
            }
        }
    }

    public static SlashRegistryContainer<ModEntityConfig> ModEntityConfigs() {
        return getRegistry(SlashRegistryType.MOD_ENTITY_CONFIGS);
    }

    public static SlashRegistryContainer<DimensionConfig> DimensionConfigs() {
        return getRegistry(SlashRegistryType.DIMENSION_CONFIGS);
    }

    public static SlashRegistryContainer<BaseItemModification> ItemModifications() {
        return getRegistry(SlashRegistryType.ITEM_MODIFICATION);
    }

    public static SlashRegistryContainer<ConfigItem> CompatibleItems() {
        return getRegistry(SlashRegistryType.COMPATIBLE_ITEM);
    }

    public static SlashRegistryContainer<BaseStatusEffect> StatusEffects() {
        return getRegistry(SlashRegistryType.STATUS_EFFECT);
    }

    public static SlashRegistryContainer<BaseMapAffix> MapAffixes() {
        return getRegistry(SlashRegistryType.MAP_AFFIX);
    }

    public static SlashRegistryContainer<IUnique> UniqueGears() {
        return getRegistry(SlashRegistryType.UNIQUE_GEAR);
    }

    public static SlashRegistryContainer<Set> Sets() {
        return getRegistry(SlashRegistryType.SET);
    }

    public static SlashRegistryContainer<Prefix> Prefixes() {
        return getRegistry(SlashRegistryType.PREFIX);
    }

    public static SlashRegistryContainer<Suffix> Suffixes() {
        return getRegistry(SlashRegistryType.SUFFIX);
    }

    public static SlashRegistryContainer<GearItemSlot> GearTypes() {
        return getRegistry(SlashRegistryType.GEAR_TYPE);
    }

    public static SlashRegistryContainer<BaseSpell> Spells() {
        return getRegistry(SlashRegistryType.SPELL);
    }

    public static SlashRegistryContainer<BaseRuneItem> Runes() {
        return getRegistry(SlashRegistryType.RUNE);
    }

    public static SlashRegistryContainer<RuneWord> RuneWords() {
        return getRegistry(SlashRegistryType.RUNEWORD);
    }

    public static SlashRegistryContainer<BaseWorldProvider> WorldProviders() {
        return getRegistry(SlashRegistryType.WORLD_PROVIDER);
    }

    public static SlashRegistryContainer<Stat> Stats() {
        return getRegistry(SlashRegistryType.STAT);
    }

    public static SlashRegistryContainer<StatMod> StatMods() {
        return getRegistry(SlashRegistryType.STATMOD);
    }

    private static HashMap<SlashRegistryType, SlashRegistryContainer> map = new HashMap<>();

    public static SlashRegistryContainer getRegistry(SlashRegistryType type) {
        return map.get(type);
    }

    public static ISlashRegistryEntry get(SlashRegistryType type, String guid) {
        return getRegistry(type).get(guid);

    }

    public static void init() {
        createRegistries();
        registerFromAllInits();
    }

    private static void registerFromAllInits() {

        new Spells().registerAll(); // some stats are based on spells, so spells go first
        new Stats().registerAll();// STATS MUST BE INIT before STATMODS  cus statmods ARE DERIVED FROM STATS, or should be at least
        new StatMods().registerAll();
        new Runes().registerAll();
        new RuneWords().registerAll();
        new GearTypes().registerAll();
        new MapAffixes().registerAll();
        new Prefixes().registerAll();
        new Suffixes().registerAll();
        new StatusEffects().registerAll();
        new UniqueGears().registerAll();
        new WorldProviders().registerAll();
        new Sets().registerAll();
        new ItemModifications().registerAll();
    }

    private static void createRegistries() {
        map.put(SlashRegistryType.GEAR_TYPE, new SlashRegistryContainer<GearItemSlot>(SlashRegistryType.GEAR_TYPE, new EmptyGearType()));
        map.put(SlashRegistryType.STAT, new SlashRegistryContainer<Stat>(SlashRegistryType.STAT, new EmptyStat()));
        map.put(SlashRegistryType.STATMOD, new SlashRegistryContainer<StatMod>(SlashRegistryType.STATMOD, new EmptyStatMod()));
        map.put(SlashRegistryType.SET, new SlashRegistryContainer<Set>(SlashRegistryType.SET, new EmptySet()));
        map.put(SlashRegistryType.SPELL, new SlashRegistryContainer<BaseSpell>(SlashRegistryType.SPELL, new EmptySpell()));
        map.put(SlashRegistryType.UNIQUE_GEAR, new SlashRegistryContainer<IUnique>(SlashRegistryType.UNIQUE_GEAR, new EmptyUnique()));
        map.put(SlashRegistryType.SUFFIX, new SlashRegistryContainer<Suffix>(SlashRegistryType.SUFFIX, new EmptySuffix()));
        map.put(SlashRegistryType.PREFIX, new SlashRegistryContainer<Prefix>(SlashRegistryType.PREFIX, new EmptyPrefix()));
        map.put(SlashRegistryType.RUNE, new SlashRegistryContainer<BaseRuneItem>(SlashRegistryType.RUNE, new EmptyRune(0)));
        map.put(SlashRegistryType.RUNEWORD, new SlashRegistryContainer<RuneWord>(SlashRegistryType.RUNEWORD, new EmptyRuneWord()));
        map.put(SlashRegistryType.MAP_AFFIX, new SlashRegistryContainer<BaseMapAffix>(SlashRegistryType.MAP_AFFIX, new EmptyMapAffix()));
        map.put(SlashRegistryType.STATUS_EFFECT, new SlashRegistryContainer<BaseStatusEffect>(SlashRegistryType.STATUS_EFFECT, new EmptyStatusEffect()));
        map.put(SlashRegistryType.WORLD_PROVIDER, new SlashRegistryContainer<BaseWorldProvider>(SlashRegistryType.WORLD_PROVIDER, new BirchForestIWP(null, null)));
        map.put(SlashRegistryType.ITEM_MODIFICATION, new SlashRegistryContainer<BaseItemModification>(SlashRegistryType.ITEM_MODIFICATION, new AddChaosStatMod()));
        map.put(SlashRegistryType.COMPATIBLE_ITEM, new SlashRegistryContainer<ConfigItem>(SlashRegistryType.COMPATIBLE_ITEM, new ConfigItem())
                .dontErrorIfEmpty()
                .logAdditions());
        map.put(SlashRegistryType.DIMENSION_CONFIGS, new SlashRegistryContainer<DimensionConfig>(SlashRegistryType.DIMENSION_CONFIGS, DimensionConfig
                .DefaultExtra()).logAdditions());
        map.put(SlashRegistryType.MOD_ENTITY_CONFIGS, new SlashRegistryContainer<ModEntityConfig>(SlashRegistryType.MOD_ENTITY_CONFIGS, new ModEntityConfig())
                .logAdditions());

    }

}
