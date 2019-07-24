package com.robertx22.mine_and_slash.database.unique_items.necklaces;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.HealthFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.HealthRegenFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalResistFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalSpellDamageFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalTransferFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.percent.HealthPercent;
import com.robertx22.mine_and_slash.database.stats.stat_mods.percent.much_less.CrippleDodgePercent;
import com.robertx22.mine_and_slash.database.unique_items.bases.BaseUniqueNecklace;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

import java.util.Arrays;
import java.util.List;

public class NecklaceNature extends BaseUniqueNecklace {

    public NecklaceNature() {

    }

    @Override
    public int Tier() {
        return 7;
    }

    @Override
    public String GUID() {
        return "necklacenature0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new ElementalSpellDamageFlat(Elements.Nature), new HealthFlat(), new HealthRegenFlat(), new ElementalTransferFlat(Elements.Fire, Elements.Nature), new ElementalResistFlat(Elements.Fire), new HealthPercent(), new CrippleDodgePercent());
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Amulet of the Oak";
    }

    @Override
    public String locDescForLangFile() {
        return "I seek strength only in nature.";
    }
}
