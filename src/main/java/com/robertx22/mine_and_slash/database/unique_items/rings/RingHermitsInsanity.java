package com.robertx22.mine_and_slash.database.unique_items.rings;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.curios.Ring;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalPeneFlat;
import com.robertx22.mine_and_slash.database.stats.mods.percent.HealthPercent;
import com.robertx22.mine_and_slash.database.stats.mods.percent.offense.CriticalDamagePercent;
import com.robertx22.mine_and_slash.database.unique_items.IUnique;
import com.robertx22.mine_and_slash.database.unique_items.StatReq;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;

import java.util.Arrays;
import java.util.List;

public class RingHermitsInsanity implements IUnique {

    static StatReq req = new StatReq(
        LvlPointStat.STRENGTH, StatReq.Size.MEDIUM, LvlPointStat.INTELLIGENCE, StatReq.Size.MEDIUM);

    @Override
    public StatReq getRequirements() {
        return req;
    }

    @Override
    public GearItemSlot getGearSlot() {
        return Ring.INSTANCE;
    }

    @Override
    public int Tier() {
        return 4;
    }

    @Override
    public String GUID() {
        return "ring_hermit_insanity";
    }

    @Override
    public int getRarityRank() {
        return IRarity.Legendary;
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new HealthPercent().size(StatMod.Size.TRIPLE_LESS), new CriticalDamagePercent().size(StatMod.Size.TRIPLE));
    }

    @Override
    public List<StatMod> primaryStats() {
        return Arrays.asList(new ElementalPeneFlat(Elements.Elemental).size(StatMod.Size.HALF_MORE));
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Hermit's Ring of Insanity";
    }

    @Override
    public String locDescForLangFile() {
        return "Stop! Don't approach me..";
    }

}
