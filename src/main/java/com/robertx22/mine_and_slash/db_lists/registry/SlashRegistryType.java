package com.robertx22.mine_and_slash.db_lists.registry;

import com.robertx22.mine_and_slash.config.compatible_items.ConfigItem;
import com.robertx22.mine_and_slash.database.affixes.Prefix;
import com.robertx22.mine_and_slash.database.affixes.Suffix;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.map_affixes.BaseMapAffix;
import com.robertx22.mine_and_slash.database.runewords.RuneWord;
import com.robertx22.mine_and_slash.database.sets.Set;
import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.status_effects.bases.BaseStatusEffect;
import com.robertx22.mine_and_slash.database.unique_items.IUnique;
import com.robertx22.mine_and_slash.database.world_providers.BaseWorldProvider;
import com.robertx22.mine_and_slash.items.runes.base.BaseRuneItem;
import com.robertx22.mine_and_slash.spells.bases.BaseSpell;

public enum SlashRegistryType {

    STAT(Stat.class),
    STATMOD(StatMod.class),
    RUNE(BaseRuneItem.class),
    RUNEWORD(RuneWord.class),
    STATUS_EFFECT(BaseStatusEffect.class),
    GEAR_TYPE(GearItemSlot.class),
    SPELL(BaseSpell.class),
    SUFFIX(Suffix.class),
    PREFIX(Prefix.class),
    UNIQUE_GEAR(IUnique.class),
    WORLD_PROVIDER(BaseWorldProvider.class),
    SET(Set.class),
    EMPTY(EmptySlashRegistry.class),
    MAP_AFFIX(BaseMapAffix.class),
    COMPATIBLE_ITEM(ConfigItem.class);

    public Class<? extends ISlashRegistryEntry> theclass;

    SlashRegistryType(Class<? extends ISlashRegistryEntry> theclass) {
        this.theclass = theclass;
    }

}
