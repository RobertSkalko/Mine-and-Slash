package com.robertx22.mine_and_slash.database.items.unique_items.necklaces;

import com.robertx22.mine_and_slash.database.items.unique_items.StatReq;
import com.robertx22.mine_and_slash.database.items.unique_items.bases.BaseUniqueNecklace;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.ArmorFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.corestats.StrengthFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.LifestealFlat;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;

import java.util.Arrays;
import java.util.List;

public class NecklaceStrength extends BaseUniqueNecklace {

    static StatReq req = new StatReq(new StatReq(LvlPointStat.WISDOM, StatReq.Size.TINY),
                                     new StatReq(LvlPointStat.VITALITY, StatReq.Size.TINY),
                                     new StatReq(LvlPointStat.STAMINA, StatReq.Size.TINY)
    );

    private NecklaceStrength() {
    }

    public static NecklaceStrength getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public StatReq getRequirements() {
        return req;
    }

    @Override
    public int Tier() {
        return 4;
    }

    @Override
    public String GUID() {
        return "necklace_strength0";
    }

    @Override
    public int getRarityRank() {
        return IRarity.Epic;
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new ArmorFlat(), new LifestealFlat());
    }

    @Override
    public List<StatMod> primaryStats() {
        return Arrays.asList(new StrengthFlat().multi(5));
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Hidden Strength Amulet";
    }

    @Override
    public String locDescForLangFile() {
        return "Search everywhere to find what you truly need.";
    }

    private static class SingletonHolder {
        private static final NecklaceStrength INSTANCE = new NecklaceStrength();
    }
}
