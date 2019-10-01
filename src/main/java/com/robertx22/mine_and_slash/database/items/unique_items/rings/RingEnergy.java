package com.robertx22.mine_and_slash.database.items.unique_items.rings;

import com.robertx22.mine_and_slash.database.items.unique_items.StatReq;
import com.robertx22.mine_and_slash.database.items.unique_items.bases.BaseUniqueRing;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.offense.SpellDamageFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.EnergyRegenFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.percent.EnergyRegenPercent;
import com.robertx22.mine_and_slash.database.stats.stat_mods.percent.much_less.CrippleCriticalDamagePercent;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;

import java.util.Arrays;
import java.util.List;

public class RingEnergy extends BaseUniqueRing {

    public RingEnergy() {

    }

    static StatReq req = new StatReq(LvlPointStat.STAMINA, StatReq.Size.NORMAL);

    @Override
    public StatReq getRequirements() {
        return req;
    }

    @Override
    public int Tier() {
        return 15;
    }

    @Override
    public String GUID() {
        return "ringenergy0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new EnergyRegenFlat(), new EnergyRegenPercent().multi(2.5F), new CrippleCriticalDamagePercent());
    }

    @Override
    public List<StatMod> primaryStats() {
        return Arrays.asList(new SpellDamageFlat().multi(0.5F));
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Ring of Unlimited Endurance";
    }

    @Override
    public String locDescForLangFile() {
        return "I will pay any price to continue!";
    }
}
