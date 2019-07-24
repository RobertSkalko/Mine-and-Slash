package com.robertx22.mine_and_slash.database.unique_items.bracelets;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.offense.SpellDamageFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.LootTypeBonusFlat;
import com.robertx22.mine_and_slash.database.unique_items.bases.BaseUniqueBracelet;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;
import com.robertx22.mine_and_slash.uncommon.enumclasses.LootType;

import java.util.Arrays;
import java.util.List;

public class BraceletSetDrop extends BaseUniqueBracelet {

    public BraceletSetDrop() {

    }

    @Override
    public int Tier() {
        return 6;
    }

    @Override
    public boolean canGetSet() {
        return true;
    }

    @Override
    public String GUID() {
        return "braceletsetdrop0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new SpellDamageFlat(), new LootTypeBonusFlat(LootType.UniqueItem));
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Gloves of Excavation";
    }

    @Override
    public String locDescForLangFile() {
        return "No matter the depths, it will be mine!";
    }
}
