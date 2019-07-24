package com.robertx22.mine_and_slash.database.unique_items.chest;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.HealthFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalResistFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalSpellDamageFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalTransferFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.percent.much_less.CrippleLifeOnHitPercent;
import com.robertx22.mine_and_slash.database.stats.stat_mods.percent.much_less.CrippleLifestealPercent;
import com.robertx22.mine_and_slash.database.unique_items.bases.BaseUniqueChest;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

import java.util.Arrays;
import java.util.List;

public class ChestFire extends BaseUniqueChest {

    public ChestFire() {

    }

    @Override
    public int Tier() {
        return 6;

    }

    @Override
    public String GUID() {
        return "chestfire0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new HealthFlat(), new ElementalResistFlat(Elements.Fire), new ElementalSpellDamageFlat(Elements.Fire), new ElementalTransferFlat(Elements.Nature, Elements.Fire), new CrippleLifeOnHitPercent(), new CrippleLifestealPercent());
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Burning Man Chestplate";
    }

    @Override
    public String locDescForLangFile() {
        return "What can't kill me only makes me glow brighter.";
    }
}