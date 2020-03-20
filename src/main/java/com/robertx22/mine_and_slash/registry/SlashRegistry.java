package com.robertx22.mine_and_slash.registry;

import com.google.common.collect.Lists;
import com.robertx22.mine_and_slash.config.dimension_configs.DimensionConfig;
import com.robertx22.mine_and_slash.config.whole_mod_entity_configs.ModEntityConfig;
import com.robertx22.mine_and_slash.data_generation.compatible_items.CompatibleItem;
import com.robertx22.mine_and_slash.database.affixes.BaseAffix;
import com.robertx22.mine_and_slash.database.bosses.base.Boss;
import com.robertx22.mine_and_slash.database.bosses.impl.NecromancerBoss;
import com.robertx22.mine_and_slash.database.currency.OrbOfTransmutationItem;
import com.robertx22.mine_and_slash.database.currency.base.CurrencyItem;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.item_modifications.bases.BaseItemModification;
import com.robertx22.mine_and_slash.database.item_modifications.gear_items.AddChaosStatMod;
import com.robertx22.mine_and_slash.database.loot_crates.CommonerCrate;
import com.robertx22.mine_and_slash.database.loot_crates.bases.LootCrate;
import com.robertx22.mine_and_slash.database.map_affixes.BaseMapAffix;
import com.robertx22.mine_and_slash.database.map_events.base.MapEvent;
import com.robertx22.mine_and_slash.database.map_events.impl.ZombieHordeEvent;
import com.robertx22.mine_and_slash.database.runes.base.BaseRune;
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
import com.robertx22.mine_and_slash.database.unique_items.IUnique;
import com.robertx22.mine_and_slash.database.world_providers.BaseWorldProvider;
import com.robertx22.mine_and_slash.database.world_providers.DungeonIWP;
import com.robertx22.mine_and_slash.db_lists.initializers.*;
import com.robertx22.mine_and_slash.dimensions.MapManager;
import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.onevent.data_gen.ISerializedRegistryEntry;
import com.robertx22.mine_and_slash.packets.RegistryPacket;
import com.robertx22.mine_and_slash.registry.empty_entries.*;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.world.IWorld;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SlashRegistry {

    private static HashMap<SlashRegistryType, SlashRegistryContainer> SERVER = new HashMap<>();
    private static HashMap<SlashRegistryType, SlashRegistryContainer> BACKUP = new HashMap<>();

    public static void backup() {
        BACKUP = new HashMap<>(SERVER);
    }

    public static void restoreBackup() {
        SERVER = new HashMap<>(BACKUP);
    }

    public static void restoreFromBackupifEmpty() {
        if (UniqueGears().isEmpty() || Runes().isEmpty() || Affixes().isEmpty()) {
            restoreBackup();
        }
    }

    public static DimensionConfig getDimensionConfig(IWorld world) {
        String id = MapManager.getId(world);
        return DimensionConfigs().get(id);
    }

    public static ModEntityConfig getEntityConfig(LivingEntity entity, EntityCap.UnitData data) {

        String monster_id = entity.getType()
            .getRegistryName()
            .toString();
        String mod_id = entity.getType()
            .getRegistryName()
            .getNamespace();

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

    public static SlashRegistryContainer<CurrencyItem> CurrencyItems() {
        return getRegistry(SlashRegistryType.CURRENCY_ITEMS);
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

    public static SlashRegistryContainer<CompatibleItem> CompatibleItems() {
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

    public static SlashRegistryContainer<BaseAffix> Affixes() {
        return getRegistry(SlashRegistryType.AFFIX);
    }

    public static SlashRegistryContainer<GearItemSlot> GearTypes() {
        return getRegistry(SlashRegistryType.GEAR_TYPE);
    }

    public static SlashRegistryContainer<BaseSpell> Spells() {
        return getRegistry(SlashRegistryType.SPELL);
    }

    public static SlashRegistryContainer<BaseRune> Runes() {
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

    public static List<SlashRegistryContainer> getAllRegistries() {
        return new ArrayList<>(SERVER.values());
    }

    public static SlashRegistryContainer getRegistry(SlashRegistryType type) {
        return SERVER.get(type);
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
            e.getCause()
                .printStackTrace();
        }
    }

    public static void sendAllPacketsToClientOnLogin(ServerPlayerEntity player) {

        getAllRegistries()
            .forEach(x -> {
                if (x.getType()
                    .getEmpty() != null) {
                    try {

                        List<ISerializedRegistryEntry> list = x.getFromDatapacks();

                        if (list.size() < 100) {
                            MMORPG.sendToClient(new RegistryPacket(x.getType(), list), player);
                        } else {
                            for (List<ISerializedRegistryEntry> part : Lists.partition(list, 100)) {
                                MMORPG.sendToClient(new RegistryPacket(x.getType(), part), player);
                            }

                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
    }

    public static void checkGuidValidity() {

        SERVER.values()
            .forEach(c -> c.getAllIncludingSeriazable()
                .forEach(x -> {
                    ISlashRegistryEntry entry = (ISlashRegistryEntry) x;
                    if (!entry.isGuidFormattedCorrectly()) {
                        throw new RuntimeException(entry.getInvalidGuidMessage());
                    }
                }));

    }

    public static void unregisterInvalidEntries() {

        System.out.println("Starting Mine and Slash Registry auto validation.");

        List<ISlashRegistryEntry> invalid = new ArrayList<>();

        SERVER.values()
            .forEach(c -> c.getList()
                .forEach(x -> {
                    ISlashRegistryEntry entry = (ISlashRegistryEntry) x;
                    if (!entry.isRegistryEntryValid()) {
                        invalid.add(entry);
                    }
                }));

        invalid.forEach(x -> x.unregisterDueToInvalidity());

        if (invalid.isEmpty()) {
            System.out.println("All Mine and Slash registries appear valid.");
        } else {
            System.out.println(invalid.size() + " Mine and Slash entries are INVALID!");
        }
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

        new PerkEffectsInit().registerAll();
        new SpellPerkEffectsInit().registerAll();

        new Perks().registerAll();
        new SpellPerks().registerAll();

        new LootCrates().registerAll();

        new Bosses().registerAll();
        new MapEvents().registerAll();

    }

    private static void addRegistry(SlashRegistryContainer cont) {
        SERVER.put(cont.getType(), cont);
    }

    public static void initRegistries() {
        SERVER = new HashMap<>();

        // data pack ones
        addRegistry(new SlashRegistryContainer<BaseRune>(SlashRegistryType.RUNE, EmptyRune.getInstance()).isDatapack());
        addRegistry(new SlashRegistryContainer<IUnique>(SlashRegistryType.UNIQUE_GEAR, EmptyUnique.getInstance()).isDatapack());
        addRegistry(new SlashRegistryContainer<BaseAffix>(SlashRegistryType.AFFIX, EmptyAffix.getInstance()).isDatapack());
        addRegistry(new SlashRegistryContainer<RuneWord>(SlashRegistryType.RUNEWORD, EmptyRuneWord.getInstance()).isDatapack());
        addRegistry(new SlashRegistryContainer<Set>(SlashRegistryType.SET, new EmptySet()).isDatapack());
        addRegistry(new SlashRegistryContainer<CompatibleItem>(SlashRegistryType.COMPATIBLE_ITEM,
            CompatibleItem.EMPTY).dontErrorIfEmpty()
            .isDatapack()
            .logAdditions());
        // data pack ones

        addRegistry(new SlashRegistryContainer<GearItemSlot>(SlashRegistryType.GEAR_TYPE, new EmptyGearType()));
        addRegistry(new SlashRegistryContainer<Stat>(SlashRegistryType.STAT, EmptyStat.getInstance()));
        addRegistry(new SlashRegistryContainer<StatMod>(SlashRegistryType.STATMOD, EmptyStatMod.getInstance()));
        addRegistry(new SlashRegistryContainer<BaseSpell>(SlashRegistryType.SPELL, new EmptySpell()));
        addRegistry(new SlashRegistryContainer<BaseMapAffix>(SlashRegistryType.MAP_AFFIX, new EmptyMapAffix()));
        addRegistry(new SlashRegistryContainer<BaseStatusEffect>(SlashRegistryType.STATUS_EFFECT, new EmptyStatusEffect()));
        addRegistry(new SlashRegistryContainer<BaseWorldProvider>(SlashRegistryType.WORLD_PROVIDER, new DungeonIWP(null, null)));
        addRegistry(new SlashRegistryContainer<BaseItemModification>(SlashRegistryType.ITEM_MODIFICATION, new AddChaosStatMod()));
        addRegistry(new SlashRegistryContainer<CurrencyItem>(SlashRegistryType.CURRENCY_ITEMS, new OrbOfTransmutationItem()));
        addRegistry(new SlashRegistryContainer<DimensionConfig>(SlashRegistryType.DIMENSION_CONFIGS, DimensionConfig.DefaultExtra()
            ).logAdditions()
                .dontErrorMissingEntriesOnAccess()
        );
        addRegistry(new ModEntityContainer(SlashRegistryType.MOD_ENTITY_CONFIGS).logAdditions());
        addRegistry(new SlashRegistryContainer<Perk>(SlashRegistryType.PERK, null));
        addRegistry(new SlashRegistryContainer<PerkEffect>(SlashRegistryType.PERK_EFFECT, StartPerkEffects.GUARDIAN));
        addRegistry(new SlashRegistryContainer<LootCrate>(SlashRegistryType.LOOT_CRATE, CommonerCrate.INSTANCE));
        addRegistry(new SlashRegistryContainer<SpellPerk>(SlashRegistryType.SPELL_PERK, null));
        addRegistry(new SlashRegistryContainer<SpellPerkEffect>(SlashRegistryType.SPELL_PERK_EFFECT, null));
        addRegistry(new SlashRegistryContainer<SynergyPerkEffect>(SlashRegistryType.SYNERGY_EFFECT, null));
        addRegistry(new SlashRegistryContainer<Boss>(SlashRegistryType.BOSS, NecromancerBoss.getInstance()));
        addRegistry(new SlashRegistryContainer<MapEvent>(SlashRegistryType.MAP_EVENT, ZombieHordeEvent.getInstance()));
    }

}
