package com.robertx22.mine_and_slash.database.items.unique_items.boots;

import com.robertx22.mine_and_slash.database.items.unique_items.bases.BaseUniqueBoots;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.HealthFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalResistFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalSpellToAttackDMGFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.WeaponDamageFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.percent.much_less.CrippleLifestealPercent;
import com.robertx22.mine_and_slash.database.stats.stat_mods.percent.offense.CriticalDamagePercent;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;

import java.util.Arrays;
import java.util.List;

public class BootsWater extends BaseUniqueBoots {

    public BootsWater() {

    }

    @Override
    public int Tier() {
        return 18;
    }

    @Override
    public String GUID() {
        return "bootswater0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new WeaponDamageFlat(WeaponTypes.Sword), new ElementalSpellToAttackDMGFlat(Elements.Water)
                .multi(2), new CriticalDamagePercent(), new ElementalResistFlat(Elements.Water), new CrippleLifestealPercent());

    }

    @Override
    public List<StatMod> primaryStats() {
        return Arrays.asList(new HealthFlat());
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Ice Steps";
    }

    @Override
    public String locDescForLangFile() {
        return "Ice forms wherever I walk.";
    }
}
