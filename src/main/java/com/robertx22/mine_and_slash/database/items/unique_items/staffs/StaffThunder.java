package com.robertx22.mine_and_slash.database.items.unique_items.staffs;

import com.robertx22.mine_and_slash.database.items.unique_items.StatReq;
import com.robertx22.mine_and_slash.database.items.unique_items.bases.BaseUniqueStaff;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.CriticalDamageFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.ManaOnHitFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalAttackDamageFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalPenePercent;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;

import java.util.Arrays;
import java.util.List;

public class StaffThunder extends BaseUniqueStaff {

    public StaffThunder() {

    }

    static StatReq req = new StatReq(LvlPointStat.STAMINA, StatReq.Size.MEDIUM, LvlPointStat.INTELLIGENCE, StatReq.Size.MEDIUM);

    @Override
    public StatReq getRequirements() {
        return req;
    }

    @Override
    public int Tier() {
        return 5;
    }

    @Override
    public String GUID() {
        return "uniquestaffthunder0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new CriticalDamageFlat(), new ElementalPenePercent(Elements.Thunder), new ManaOnHitFlat()
                .multi(1.5F));
    }

    @Override
    public List<StatMod> primaryStats() {
        return Arrays.asList(new ElementalAttackDamageFlat(Elements.Thunder));
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Thunderstorm Staff";
    }

    @Override
    public String locDescForLangFile() {
        return "Controlled power can bring both energy and destruction.";
    }
}
