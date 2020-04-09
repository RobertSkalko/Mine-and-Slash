package com.robertx22.mine_and_slash.database.unique_items.charms;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.curios.Charm;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalPeneFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalResistFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalSpellDamageFlat;
import com.robertx22.mine_and_slash.database.stats.mods.percent.ManaRegenPercent;
import com.robertx22.mine_and_slash.database.unique_items.IUnique;
import com.robertx22.mine_and_slash.database.unique_items.StatReq;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;

import java.util.Arrays;
import java.util.List;

public class CharmThunder implements IUnique {

    public CharmThunder() {

    }

    static StatReq req = new StatReq(
        LvlPointStat.WISDOM, StatReq.Size.MEDIUM, LvlPointStat.INTELLIGENCE, StatReq.Size.MEDIUM);

    @Override
    public StatReq getRequirements() {
        return req;
    }

    @Override
    public int getTier() {
        return 5;
    }

    @Override
    public String GUID() {
        return "charmthunder0";
    }

    @Override
    public GearItemSlot getGearSlot() {
        return Charm.INSTANCE;
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(
            new ElementalResistFlat(Elements.Thunder),
            new ManaRegenPercent(),
            new ElementalPeneFlat(Elements.Thunder)
        );
    }

    @Override
    public List<StatMod> primaryStats() {
        return Arrays.asList(new ElementalSpellDamageFlat(Elements.Thunder));
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Charm of Heavenly Tribulations";
    }

    @Override
    public String locDescForLangFile() {
        return "Jade is only worth after it is polished.";
    }
}
