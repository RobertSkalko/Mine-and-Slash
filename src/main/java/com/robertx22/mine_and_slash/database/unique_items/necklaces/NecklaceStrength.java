package com.robertx22.mine_and_slash.database.unique_items.necklaces;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.curios.Necklace;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.corestats.CoreStatFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.defense.ArmorFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.LifestealFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.MagicStealFlat;
import com.robertx22.mine_and_slash.database.stats.types.core_stats.Strength;
import com.robertx22.mine_and_slash.database.unique_items.IUnique;
import com.robertx22.mine_and_slash.database.unique_items.StatReq;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;

import java.util.Arrays;
import java.util.List;

public class NecklaceStrength implements IUnique {

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
    public GearItemSlot getGearSlot() {
        return Necklace.INSTANCE;
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
        return Arrays.asList(
            new ArmorFlat().size(StatMod.Size.VERY_HIGH),
            new LifestealFlat().size(StatMod.Size.HIGH),
            new MagicStealFlat().size(StatMod.Size.HIGH));
    }

    @Override
    public List<StatMod> primaryStats() {
        return Arrays.asList(new CoreStatFlat(Strength.INSTANCE).size(StatMod.Size.VERY_HIGH));
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
