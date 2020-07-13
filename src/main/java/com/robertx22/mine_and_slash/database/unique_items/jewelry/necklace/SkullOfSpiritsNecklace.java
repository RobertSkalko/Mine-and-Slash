package com.robertx22.mine_and_slash.database.unique_items.jewelry.necklace;

import com.robertx22.mine_and_slash.database.StatModifier;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.curios.OccultNecklace;
import com.robertx22.mine_and_slash.database.stats.types.core_stats.AllAttributes;
import com.robertx22.mine_and_slash.database.stats.types.generated.ElementalResist;
import com.robertx22.mine_and_slash.database.stats.types.resources.MagicShieldRegen;
import com.robertx22.mine_and_slash.database.unique_items.IUnique;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.ModType;

import java.util.Arrays;
import java.util.List;

public class SkullOfSpiritsNecklace implements IUnique {
    @Override
    public List<StatModifier> uniqueStats() {
        return Arrays.asList(
            new StatModifier(4, 18, AllAttributes.getInstance(), ModType.FLAT),
            new StatModifier(15, 30, MagicShieldRegen.getInstance(), ModType.LOCAL_INCREASE),
            new StatModifier(-5, -15, new ElementalResist(Elements.Water), ModType.FLAT),
            new StatModifier(-5, -15, new ElementalResist(Elements.Fire), ModType.FLAT)
        );
    }

    @Override
    public String locDescForLangFile() {
        return "The mysterious skull contains knowledge of the contained spirits, but also their madness.";
    }

    @Override
    public String locNameForLangFile() {
        return "Skull of Spirits";
    }

    @Override
    public String GUID() {
        return "skull_of_spirits";
    }

    @Override
    public GearItemSlot getGearSlot() {
        return OccultNecklace.INSTANCE;
    }
}
