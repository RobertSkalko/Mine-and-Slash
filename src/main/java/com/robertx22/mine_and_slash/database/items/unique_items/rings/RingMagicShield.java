package com.robertx22.mine_and_slash.database.items.unique_items.rings;

import com.robertx22.mine_and_slash.database.items.unique_items.StatReq;
import com.robertx22.mine_and_slash.database.items.unique_items.bases.BaseUniqueRing;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.corestats.IntelligenceFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.MagicShieldFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.MagicShieldRegenFlat;
import com.robertx22.mine_and_slash.database.stats.mods.percent.MagicShieldPercent;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;

import java.util.Arrays;
import java.util.List;

public class RingMagicShield extends BaseUniqueRing {

    public static RingMagicShield INSTANCE = new RingMagicShield();

    private RingMagicShield() {

    }
    
    static StatReq req = new StatReq(LvlPointStat.INTELLIGENCE, StatReq.Size.MEDIUM);

    @Override
    public StatReq getRequirements() {
        return req;
    }

    @Override
    public int Tier() {
        return 3;
    }

    @Override
    public String GUID() {
        return "ring_magic_shield0";
    }

    @Override
    public int getRarityRank() {
        return IRarity.Rare;
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new MagicShieldPercent().multi(4), new MagicShieldRegenFlat(), new IntelligenceFlat());
    }

    @Override
    public List<StatMod> primaryStats() {
        return Arrays.asList(new MagicShieldFlat().multi(0.5F));
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Tower of Will";
    }

    @Override
    public String locDescForLangFile() {
        return "Will is an unshakeable fortress.";
    }
}
