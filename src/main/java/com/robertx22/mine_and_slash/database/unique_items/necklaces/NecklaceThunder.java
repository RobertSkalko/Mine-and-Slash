package com.robertx22.mine_and_slash.database.unique_items.necklaces;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.less.LessHealthRegenFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.EnergyRegenFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalResistFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalSpellDamageFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalSpellToAttackDMGFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalTransferFlat;
import com.robertx22.mine_and_slash.database.unique_items.bases.BaseUniqueNecklace;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

import java.util.Arrays;
import java.util.List;

public class NecklaceThunder extends BaseUniqueNecklace {

    public NecklaceThunder() {

    }

    @Override
    public int Tier() {
        return 18;
    }

    @Override
    public String GUID() {
        return "necklacethunder0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new ElementalSpellDamageFlat(Elements.Thunder), new ElementalSpellToAttackDMGFlat(Elements.Thunder), new ElementalTransferFlat(Elements.Nature, Elements.Thunder), new ElementalResistFlat(Elements.Nature), new EnergyRegenFlat(), new LessHealthRegenFlat());

    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Amulet of the Thunderstorm";
    }

    @Override
    public String locDescForLangFile() {
        return "Command Thunder, command Energy.";
    }
}
