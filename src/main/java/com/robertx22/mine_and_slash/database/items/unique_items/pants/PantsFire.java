package com.robertx22.mine_and_slash.database.items.unique_items.pants;

import com.robertx22.mine_and_slash.database.items.unique_items.StatReq;
import com.robertx22.mine_and_slash.database.items.unique_items.bases.BaseUniquePantsItem;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.ArmorFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalAffinityFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalTransferFlat;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;

import java.util.Arrays;
import java.util.List;

public class PantsFire extends BaseUniquePantsItem {

    public PantsFire() {

    }

    static StatReq req = new StatReq(LvlPointStat.STAMINA, StatReq.Size.NORMAL, LvlPointStat.INTELLIGENCE, StatReq.Size.SMALL);

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
        return "pantsfire0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new ArmorFlat().multi(3), new ElementalTransferFlat(Elements.Nature, Elements.Fire));
    }

    @Override
    public List<StatMod> primaryStats() {
        return Arrays.asList(new ElementalAffinityFlat(Elements.Fire));
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Flaming Leggings";
    }

    @Override
    public String locDescForLangFile() {
        return "Embrace my flames.";
    }
}
