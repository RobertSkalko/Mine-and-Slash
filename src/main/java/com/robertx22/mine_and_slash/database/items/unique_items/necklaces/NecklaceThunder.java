package com.robertx22.mine_and_slash.database.items.unique_items.necklaces;

import com.robertx22.mine_and_slash.database.items.unique_items.StatReq;
import com.robertx22.mine_and_slash.database.items.unique_items.bases.BaseUniqueNecklace;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.EnergyRegenFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalSpellDamageFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalSpellToAttackDMGFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalTransferFlat;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;

import java.util.Arrays;
import java.util.List;

public class NecklaceThunder extends BaseUniqueNecklace {

    public NecklaceThunder() {

    }

    static StatReq req = new StatReq(
            LvlPointStat.STAMINA, StatReq.Size.SMALL, LvlPointStat.INTELLIGENCE, StatReq.Size.MEDIUM);

    @Override
    public StatReq getRequirements() {
        return req;
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
        return Arrays.asList(
                new ElementalSpellToAttackDMGFlat(Elements.Thunder).multi(2),
                new ElementalTransferFlat(Elements.Nature, Elements.Thunder), new EnergyRegenFlat()
        );

    }

    @Override
    public List<StatMod> primaryStats() {
        return Arrays.asList(new ElementalSpellDamageFlat(Elements.Thunder));
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
