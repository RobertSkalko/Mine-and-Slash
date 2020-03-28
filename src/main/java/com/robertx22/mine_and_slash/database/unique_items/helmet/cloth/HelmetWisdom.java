package com.robertx22.mine_and_slash.database.unique_items.helmet.cloth;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.cloth.ClothHelmet;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.corestats.CoreStatFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.defense.ArmorFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.MagicShieldFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.ManaOnHitFlat;
import com.robertx22.mine_and_slash.database.stats.types.core_stats.Wisdom;
import com.robertx22.mine_and_slash.database.unique_items.IUnique;
import com.robertx22.mine_and_slash.database.unique_items.StatReq;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;

import java.util.Arrays;
import java.util.List;

public class HelmetWisdom implements IUnique {

    public static HelmetWisdom INSTANCE = new HelmetWisdom();

    private HelmetWisdom() {

    }

    static StatReq req = new StatReq(LvlPointStat.WISDOM, StatReq.Size.NORMAL);

    @Override
    public StatReq getRequirements() {
        return req;
    }

    @Override
    public boolean canGetSet() {
        return true;
    }

    @Override
    public int Tier() {
        return 12;
    }

    @Override
    public String GUID() {
        return "helmetwisdom0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new CoreStatFlat(Wisdom.INSTANCE).size(StatMod.Size.HALF_MORE), new ArmorFlat(), new ManaOnHitFlat());
    }

    @Override
    public List<StatMod> primaryStats() {
        return Arrays.asList(new MagicShieldFlat());
    }

    @Override
    public GearItemSlot getGearSlot() {
        return ClothHelmet.INSTANCE;
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Helmet of Wisdom";
    }

    @Override
    public String locDescForLangFile() {
        return "True wisdom is knowing you know nothing.";
    }

}
