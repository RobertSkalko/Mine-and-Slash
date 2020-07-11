package com.robertx22.mine_and_slash.database.unique_items.jewelry.necklace;

import com.robertx22.mine_and_slash.database.StatModifier;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.curios.LifeNecklace;
import com.robertx22.mine_and_slash.database.stats.types.core_stats.AllAttributes;
import com.robertx22.mine_and_slash.database.stats.types.resources.Health;
import com.robertx22.mine_and_slash.database.stats.types.resources.HealthRegen;
import com.robertx22.mine_and_slash.database.unique_items.IUnique;
import com.robertx22.mine_and_slash.uncommon.enumclasses.ModType;

import java.util.Arrays;
import java.util.List;

public class BirthingMiracleNecklace implements IUnique {
    @Override
    public List<StatModifier> uniqueStats() {
        return Arrays.asList(
            new StatModifier(1, 10, Health.getInstance(), ModType.FLAT),
            new StatModifier(1, 2, AllAttributes.getInstance(), ModType.FLAT),
            new StatModifier(1, 1, HealthRegen.getInstance(), ModType.FLAT)
        );
    }

    @Override
    public String locDescForLangFile() {
        return "Life can sometimes keep on giving.";
    }

    @Override
    public String locNameForLangFile() {
        return "Birthing Miracle";
    }

    @Override
    public String GUID() {
        return "birth_miracle";
    }

    @Override
    public GearItemSlot getGearSlot() {
        return LifeNecklace.INSTANCE;
    }
}

