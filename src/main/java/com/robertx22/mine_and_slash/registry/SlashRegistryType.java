package com.robertx22.mine_and_slash.registry;

import com.robertx22.mine_and_slash.data_generation.compatible_items.CompatibleItem;
import com.robertx22.mine_and_slash.database.sets.Set;
import com.robertx22.mine_and_slash.onevent.data_gen.ISerializable;
import com.robertx22.mine_and_slash.registry.empty_entries.EmptyAffix;
import com.robertx22.mine_and_slash.registry.empty_entries.EmptyRune;
import com.robertx22.mine_and_slash.registry.empty_entries.EmptyRuneWord;
import com.robertx22.mine_and_slash.registry.empty_entries.EmptyUnique;

public enum SlashRegistryType {
    STAT("stat"),
    STATMOD("stat_mod"),
    RUNE("rune") {
        @Override
        public ISerializable getEmpty() {
            return EmptyRune.getInstance();
        }
    },
    RUNEWORD("runeword") {
        @Override
        public ISerializable getEmpty() {
            return EmptyRuneWord.getInstance();
        }
    },
    STATUS_EFFECT("status_effect"),
    GEAR_TYPE("gear_type"),
    SPELL("spell"),
    AFFIX("affix") {
        @Override
        public ISerializable getEmpty() {
            return EmptyAffix.getInstance();
        }
    },
    UNIQUE_GEAR("unique_gear") {
        @Override
        public ISerializable getEmpty() {
            return EmptyUnique.getInstance();
        }
    },
    WORLD_PROVIDER("world_provider"),
    SET("item_set") {
        @Override
        public ISerializable getEmpty() {
            return Set.EMPTY;
        }
    },
    EMPTY("empty"),
    MAP_AFFIX("map_affix"),
    ITEM_MODIFICATION("item_modification"),
    DIMENSION_CONFIGS("dimension_config"),
    MOD_ENTITY_CONFIGS("mod_entiy_config"),
    CURRENCY_ITEMS("currency_item"),
    COMPATIBLE_ITEM("compatible_item") {
        @Override
        public ISerializable getEmpty() {
            return CompatibleItem.EMPTY;
        }
    },
    PERK("talent_perk"),
    PERK_EFFECT("talent_perk_effect"),
    SYNERGY_EFFECT("synergy_effect"),
    SPELL_PERK("spell_perk"),
    SPELL_PERK_EFFECT("spell_perk_effect"),
    LOOT_CRATE("loot_crate"),
    QUEST("quest"),
    QUEST_REWARD("quest_reward"),
    RECIPE("recipe"),
    BOSS("boss"),
    MAP_EVENT("map_event");

    public String id;

    SlashRegistryType(String id) {
        this.id = id;
    }

    public ISerializable getEmpty() { // TODO this could be better
        return null;
    }

    public static SlashRegistryType getFromString(String str) {
        for (SlashRegistryType type : values()) {
            if (str.equals(type.id)) {
                return type;
            }
        }
        return null;
    }

}
