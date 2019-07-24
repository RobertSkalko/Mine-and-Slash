package com.robertx22.mine_and_slash.database.unique_items.chest;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.MajorArmorFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.HealthFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalResistFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalSpellDamageFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalSpellToAttackDMGFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.percent.much_less.CrippleLifestealPercent;
import com.robertx22.mine_and_slash.database.unique_items.bases.BaseUniqueChest;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

import java.util.Arrays;
import java.util.List;

public class ChestThunder extends BaseUniqueChest {

    public ChestThunder() {

    }

    @Override
    public int Tier() {
        return 6;
    }

    @Override
    public String GUID() {
        return "chestthunder0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new HealthFlat(), new ElementalResistFlat(Elements.Thunder), new MajorArmorFlat(), new ElementalSpellDamageFlat(Elements.Thunder), new ElementalSpellToAttackDMGFlat(Elements.Thunder), new CrippleLifestealPercent());
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Armor of the Thunderstorm";
    }

    @Override
    public String locDescForLangFile() {
        return "Those who dared to follow had long since died.";
    }
}