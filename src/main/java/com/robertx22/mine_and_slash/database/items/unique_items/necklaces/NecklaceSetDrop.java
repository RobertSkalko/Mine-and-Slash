package com.robertx22.mine_and_slash.database.items.unique_items.necklaces;

import com.robertx22.mine_and_slash.database.items.unique_items.StatReq;
import com.robertx22.mine_and_slash.database.items.unique_items.bases.BaseUniqueNecklace;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.corestats.AllAttributesFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.offense.SpellDamageFlat;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;

import java.util.Arrays;
import java.util.List;

public class NecklaceSetDrop extends BaseUniqueNecklace {

    public NecklaceSetDrop() {

    }

    static StatReq req = new StatReq(LvlPointStat.WISDOM, StatReq.Size.NORMAL, LvlPointStat.STAMINA, StatReq.Size.TINY);

    @Override
    public StatReq getRequirements() {
        return req;
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
        return "necklacesetdrop0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new AllAttributesFlat());
    }

    @Override
    public List<StatMod> primaryStats() {
        return Arrays.asList(new SpellDamageFlat().multi(0.2F));
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Amulet of The Seeker";
    }

    @Override
    public String locDescForLangFile() {
        return "I seek what is buried underneath.";
    }
}
