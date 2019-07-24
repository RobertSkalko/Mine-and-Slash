package com.robertx22.mine_and_slash.database.unique_items.boots;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.HealthFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.HealthRegenFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalResistFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalTransferFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.percent.HealthPercent;
import com.robertx22.mine_and_slash.database.stats.stat_mods.percent.much_less.CrippleDodgePercent;
import com.robertx22.mine_and_slash.database.unique_items.bases.BaseUniqueBoots;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

import java.util.Arrays;
import java.util.List;

public class BootsNature extends BaseUniqueBoots {

    public BootsNature() {

    }

    @Override
    public int Tier() {
        return 7;
    }

    @Override
    public String GUID() {
        return "bootsnature0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new HealthFlat(), new HealthPercent(), new HealthRegenFlat(), new ElementalTransferFlat(Elements.Thunder, Elements.Nature), new ElementalResistFlat(Elements.Nature)
                .multi(2), new CrippleDodgePercent());

    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Tree Trunks";
    }

    @Override
    public String locDescForLangFile() {
        return "Nothing shall break my roots!";
    }
}
