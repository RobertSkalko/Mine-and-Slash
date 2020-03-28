package com.robertx22.mine_and_slash.database.unique_items.necklaces;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.curios.Necklace;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.corestats.CoreStatFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.MagicShieldRegenFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.ManaFlat;
import com.robertx22.mine_and_slash.database.stats.types.core_stats.Wisdom;
import com.robertx22.mine_and_slash.database.unique_items.IUnique;
import com.robertx22.mine_and_slash.database.unique_items.StatReq;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;

import java.util.Arrays;
import java.util.List;

public class NecklaceWisdom implements IUnique {

    static StatReq req = new StatReq(
        LvlPointStat.INTELLIGENCE, StatReq.Size.NORMAL, LvlPointStat.WISDOM, StatReq.Size.TINY);

    private NecklaceWisdom() {
    }

    public static NecklaceWisdom getInstance() {
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
        return 3;
    }

    @Override
    public String GUID() {
        return "necklace_wisdom0";
    }

    @Override
    public int getRarityRank() {
        return IRarity.Epic;
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new ManaFlat().size(StatMod.Size.HALF_MORE), new MagicShieldRegenFlat().size(StatMod.Size.HALF_MORE));
    }

    @Override
    public List<StatMod> primaryStats() {
        return Arrays.asList(new CoreStatFlat(Wisdom.INSTANCE).size(StatMod.Size.DOUBLE));
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Amulet of the Wisdom Seeker";
    }

    @Override
    public String locDescForLangFile() {
        return "Use your strengths to prop up your weaknesses.";
    }

    private static class SingletonHolder {
        private static final NecklaceWisdom INSTANCE = new NecklaceWisdom();
    }
}
