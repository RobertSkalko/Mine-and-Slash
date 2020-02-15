package com.robertx22.mine_and_slash.db_lists.registry;

import com.robertx22.mine_and_slash.config.compatible_items.ConfigItem;
import com.robertx22.mine_and_slash.config.dimension_configs.DimensionConfig;
import com.robertx22.mine_and_slash.config.whole_mod_entity_configs.ModEntityConfig;
import com.robertx22.mine_and_slash.database.affixes.BaseAffix;
import com.robertx22.mine_and_slash.database.bosses.base.Boss;
import com.robertx22.mine_and_slash.database.bosses.impl.NecromancerBoss;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.item_modifications.bases.BaseItemModification;
import com.robertx22.mine_and_slash.database.item_modifications.gear_items.AddChaosStatMod;
import com.robertx22.mine_and_slash.database.items.currency.CurrencyItem;
import com.robertx22.mine_and_slash.database.items.currency.ItemOrbOfTransmutation;
import com.robertx22.mine_and_slash.database.items.runes.base.BaseRuneItem;
import com.robertx22.mine_and_slash.database.items.runes.base.BaseUniqueRuneItem;
import com.robertx22.mine_and_slash.database.items.runes.unique_runes.PSIItem;
import com.robertx22.mine_and_slash.database.items.unique_items.IUnique;
import com.robertx22.mine_and_slash.database.loot_crates.CommonerCrate;
import com.robertx22.mine_and_slash.database.loot_crates.bases.LootCrate;
import com.robertx22.mine_and_slash.database.map_affixes.BaseMapAffix;
import com.robertx22.mine_and_slash.database.map_events.base.MapEvent;
import com.robertx22.mine_and_slash.database.map_events.impl.ZombieHordeEvent;
import com.robertx22.mine_and_slash.database.quests.base.Quest;
import com.robertx22.mine_and_slash.database.quests.base.QuestReward;
import com.robertx22.mine_and_slash.database.quests.quest_rewards.MapQuestReward;
import com.robertx22.mine_and_slash.database.quests.quests.SimpleKillMobsQuest;
import com.robertx22.mine_and_slash.database.runewords.RuneWord;
import com.robertx22.mine_and_slash.database.sets.Set;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_tree.SpellPerk;
import com.robertx22.mine_and_slash.database.spells.spell_tree.SpellPerkEffect;
import com.robertx22.mine_and_slash.database.spells.spell_tree.SynergyPerkEffect;
import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.status_effects.bases.BaseStatusEffect;
import com.robertx22.mine_and_slash.database.talent_tree.Perk;
import com.robertx22.mine_and_slash.database.talent_tree.PerkEffect;
import com.robertx22.mine_and_slash.database.talent_tree.data.StartPerkEffects;
import com.robertx22.mine_and_slash.database.world_providers.BaseWorldProvider;
import com.robertx22.mine_and_slash.database.world_providers.BirchForestIWP;
import com.robertx22.mine_and_slash.db_lists.initializers.*;
import com.robertx22.mine_and_slash.db_lists.registry.empty_entries.*;
import com.robertx22.mine_and_slash.dimensions.MapManager;
import com.robertx22.mine_and_slash.professions.recipe.BaseRecipe;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.IWorld;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SlashRegistry {

    public static DimensionConfig getDimensionConfig(IWorld world) {
        String id = MapManager.getId(world);
        return DimensionConfigs().get(id);

    }

    public static ModEntityConfig getEntityConfig(LivingEntity entity, EntityCap.UnitData data) {

        String monster_id = entity.getType().getRegistryName().toString();
        String mod_id = entity.getType().getRegistryName().getNamespace();

        ModEntityConfig config = null;

        if (EntityConfigs().isRegistered(monster_id)) {
            config = EntityConfigs().get(monster_id);
            if (config != null) {
                return config;
            }
        } else {
            if (EntityConfigs().isRegistered(mod_id)) {
                config = EntityConfigs().get(mod_id);

                if (config != null) {
                    return config;
                }

            } else {
                config = EntityConfigs().byEntityTypeDefault.get(data.getType());

                if (config != null) {
                    return config;
                }
            }
        }

        return EntityConfigs().getDefault();

    }

    public static SlashRegistryContainer<SynergyPerkEffect> SynergyEffects() {
        return getRegistry(SlashRegistryType.SYNERGY_EFFECT);
    }

    public static SlashRegistryContainer<PerkEffect> PerkEffects() {
        return getRegistry(SlashRegistryType.PERK_EFFECT);
    }

    public static SlashRegistryContainer<MapEvent> MapEvents() {
        return getRegistry(SlashRegistryType.MAP_EVENT);
    }

    public static SlashRegistryContainer<Perk> Perks() {
        return getRegistry(SlashRegistryType.PERK);
    }

    public static SlashRegistryContainer<SpellPerk> SpellPerks() {
        return getRegistry(SlashRegistryType.SPELL_PERK);
    }

    public static SlashRegistryContainer<SpellPerkEffect> SpellPerkEffects() {
        return getRegistry(SlashRegistryType.SPELL_PERK_EFFECT);
    }

    public static SlashRegistryContainer<BaseRecipe> Recipes() {
        return getRegistry(SlashRegistryType.RECIPE);
    }

    public static SlashRegistryContainer<CurrencyItem> CurrencyItems() {
        return getRegistry(SlashRegistryType.CURRENCY_ITEMS);
    }

    public static SlashRegistryContainer<BaseUniqueRuneItem> UniqueRunes() {
        return getRegistry(SlashRegistryType.UNIQUE_RUNES);
    }

    private static SlashRegistryContainer<DimensionConfig> DimensionConfigs() {
        return getRegistry(SlashRegistryType.DIMENSION_CONFIGS);
    }

    public static SlashRegistryContainer<BaseItemModification> ItemModifications() {
        return getRegistry(SlashRegistryType.ITEM_MODIFICATION);
    }

    public static SlashRegistryContainer<LootCrate> LootCrates() {
        return getRegistry(SlashRegistryType.LOOT_CRATE);
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

    public static SlashRegistryContainer<Quest> Quests() {
        return getRegistry(SlashRegistryType.QUEST);
    }

    public static SlashRegistryContainer<QuestReward> QuestRewards() {
        return getRegistry(SlashRegistryType.QUEST_REWARD);
    }

    public static SlashRegistryContainer<Set> Sets() {
        return getRegistry(SlashRegistryType.SET);
    }

    public static SlashRegistryContainer<BaseAffix> Affixes() {
        return getRegistry(SlashRegistryType.AFFIX);
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

    public static SlashRegistryContainer<Boss> Bosses() {
        return getRegistry(SlashRegistryType.BOSS);
    }

    public static SlashRegistryContainer<BaseWorldProvider> WorldProviders() {
        return getRegistry(SlashRegistryType.WORLD_PROVIDER);
    }

    public static ModEntityContainer EntityConfigs() {
        return (ModEntityContainer) getRegistry(SlashRegistryType.MOD_ENTITY_CONFIGS);
    }

    public static SlashRegistryContainer<Stat> Stats() {
        return getRegistry(SlashRegistryType.STAT);
    }

    public static SlashRegistryContainer<StatMod> StatMods() {
        return getRegistry(SlashRegistryType.STATMOD);
    }

    private static HashMap<SlashRegistryType, SlashRegistryContainer> map = new HashMap<>();

    public static List<SlashRegistryContainer> getAllRegistries() {
        return new ArrayList<>(map.values());
    }

    public static SlashRegistryContainer getRegistry(SlashRegistryType type) {
        return map.get(type);
    }

    public static ISlashRegistryEntry get(SlashRegistryType type, String guid) {
        return getRegistry(type).get(guid);

    }

    public static void registerAllItems() {
        try {
            registerFromAllInits();
        } catch (ExceptionInInitializerError e) {
            // leave this, once this error happened and we don't know why. this is to know the cause if it happens again
            e.printStackTrace();
            e.getCause().printStackTrace();
        }
    }

    public static void checkGuidValidity() {
        map.values().forEach(c -> c.getAllIncludingSeriazable().forEach(x -> {
            ISlashRegistryEntry entry = (ISlashRegistryEntry) x;
            if (!entry.isGuidFormattedCorrectly()) {
                throw new RuntimeException(entry.getInvalidGuidMessage());
            }
        }));

    }

    private static void registerFromAllInits() {

        new Spells().registerAll(); // some stats are based on spells, so spells go first
        new Stats().registerAll();// STATS MUST BE INIT before STATMODS  cus statmods ARE DERIVED FROM STATS, or
        // should be at least
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
        new CurrencyItems().registerAll();
        new UniqueRunes().registerAll();

        new PerkEffectsInit().registerAll();
        new SpellPerkEffectsInit().registerAll();

        new Perks().registerAll();
        new SpellPerks().registerAll();

        new LootCrates().registerAll();
        new Quests().registerAll();
        new QuestRewards().registerAll();

        new Bosses().registerAll();
        new MapEvents().registerAll();

    }

    public static void initRegistries() {
        map = new HashMap<>();

        map.put(SlashRegistryType.GEAR_TYPE,
                new SlashRegistryContainer<GearItemSlot>(SlashRegistryType.GEAR_TYPE, new EmptyGearType())
        );
        map.put(SlashRegistryType.STAT,
                new SlashRegistryContainer<Stat>(SlashRegistryType.STAT, EmptyStat.getInstance())
        );
        map.put(SlashRegistryType.STATMOD,
                new SlashRegistryContainer<StatMod>(SlashRegistryType.STATMOD, EmptyStatMod.getInstance())
        );
        map.put(SlashRegistryType.SET, new SlashRegistryContainer<Set>(SlashRegistryType.SET, new EmptySet()));
        map.put(SlashRegistryType.SPELL,
                new SlashRegistryContainer<BaseSpell>(SlashRegistryType.SPELL, new EmptySpell())
        );
        map.put(SlashRegistryType.UNIQUE_GEAR,
                new SlashRegistryContainer<IUnique>(SlashRegistryType.UNIQUE_GEAR, new EmptyUnique())
        );
        map.put(SlashRegistryType.UNIQUE_RUNES,
                new SlashRegistryContainer<BaseUniqueRuneItem>(SlashRegistryType.UNIQUE_RUNES, new PSIItem())
        );
        map.put(SlashRegistryType.AFFIX,
                new SlashRegistryContainer<BaseAffix>(SlashRegistryType.AFFIX, EmptyAffix.getInstance())
        );
        map.put(SlashRegistryType.RUNE,
                new SlashRegistryContainer<BaseRuneItem>(SlashRegistryType.RUNE, new EmptyRune(0))
        );
        map.put(SlashRegistryType.RUNEWORD,
                new SlashRegistryContainer<RuneWord>(SlashRegistryType.RUNEWORD, EmptyRuneWord.getInstance())
        );
        map.put(SlashRegistryType.MAP_AFFIX,
                new SlashRegistryContainer<BaseMapAffix>(SlashRegistryType.MAP_AFFIX, new EmptyMapAffix())
        );
        map.put(SlashRegistryType.STATUS_EFFECT,
                new SlashRegistryContainer<BaseStatusEffect>(SlashRegistryType.STATUS_EFFECT, new EmptyStatusEffect())
        );
        map.put(SlashRegistryType.WORLD_PROVIDER,
                new SlashRegistryContainer<BaseWorldProvider>(SlashRegistryType.WORLD_PROVIDER,
                                                              new BirchForestIWP(null, null)
                )
        );
        map.put(SlashRegistryType.ITEM_MODIFICATION,
                new SlashRegistryContainer<BaseItemModification>(SlashRegistryType.ITEM_MODIFICATION,
                                                                 new AddChaosStatMod()
                )
        );
        map.put(SlashRegistryType.CURRENCY_ITEMS,
                new SlashRegistryContainer<CurrencyItem>(SlashRegistryType.CURRENCY_ITEMS, new ItemOrbOfTransmutation())
        );
        map.put(SlashRegistryType.RECIPE, new SlashRegistryContainer<BaseRecipe>(SlashRegistryType.RECIPE, null));
        map.put(SlashRegistryType.COMPATIBLE_ITEM,
                new SlashRegistryContainer<ConfigItem>(SlashRegistryType.COMPATIBLE_ITEM,
                                                       new ConfigItem()
                ).dontErrorIfEmpty().logAdditions()
        );
        map.put(SlashRegistryType.DIMENSION_CONFIGS,
                new SlashRegistryContainer<DimensionConfig>(SlashRegistryType.DIMENSION_CONFIGS,
                                                            DimensionConfig.DefaultExtra()
                ).logAdditions()
                        .dontErrorMissingEntriesOnAccess()
        );
        map.put(SlashRegistryType.MOD_ENTITY_CONFIGS,
                new ModEntityContainer(SlashRegistryType.MOD_ENTITY_CONFIGS).logAdditions()
        );
        map.put(SlashRegistryType.PERK, new SlashRegistryContainer<Perk>(SlashRegistryType.PERK, null));
        map.put(SlashRegistryType.PERK_EFFECT,
                new SlashRegistryContainer<PerkEffect>(SlashRegistryType.PERK_EFFECT, StartPerkEffects.GUARDIAN)
        );
        map.put(SlashRegistryType.LOOT_CRATE,
                new SlashRegistryContainer<LootCrate>(SlashRegistryType.LOOT_CRATE, CommonerCrate.INSTANCE)
        );
        map.put(SlashRegistryType.QUEST,
                new SlashRegistryContainer<Quest>(SlashRegistryType.QUEST, SimpleKillMobsQuest.INSTANCE)
        );
        map.put(SlashRegistryType.QUEST_REWARD,
                new SlashRegistryContainer<QuestReward>(SlashRegistryType.QUEST_REWARD, MapQuestReward.INSTANCE)
        );
        map.put(SlashRegistryType.SPELL_PERK,
                new SlashRegistryContainer<SpellPerk>(SlashRegistryType.SPELL_PERK, null)
        );
        map.put(SlashRegistryType.SPELL_PERK_EFFECT,
                new SlashRegistryContainer<SpellPerkEffect>(SlashRegistryType.SPELL_PERK_EFFECT, null)
        );
        map.put(SlashRegistryType.SYNERGY_EFFECT,
                new SlashRegistryContainer<SynergyPerkEffect>(SlashRegistryType.SYNERGY_EFFECT, null)
        );
        map.put(SlashRegistryType.BOSS,
                new SlashRegistryContainer<Boss>(SlashRegistryType.BOSS, NecromancerBoss.getInstance())
        );
        map.put(SlashRegistryType.MAP_EVENT,
                new SlashRegistryContainer<MapEvent>(SlashRegistryType.MAP_EVENT, ZombieHordeEvent.getInstance())
        );
    }

}
