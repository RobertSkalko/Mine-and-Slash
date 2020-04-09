package com.robertx22.mine_and_slash.database.unique_items.rings;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.curios.Ring;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalResistFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalSpellDamageFlat;
import com.robertx22.mine_and_slash.database.unique_items.IUnique;
import com.robertx22.mine_and_slash.database.unique_items.StatReq;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;

import java.util.Arrays;
import java.util.List;

public class RingWaterFire implements IUnique {

    public RingWaterFire() {

    }

    static StatReq req = new StatReq(
        LvlPointStat.INTELLIGENCE, StatReq.Size.SMALL, LvlPointStat.STRENGTH, StatReq.Size.SMALL);

    @Override
    public StatReq getRequirements() {
        return req;
    }

    @Override
    public GearItemSlot getGearSlot() {
        return Ring.INSTANCE;
    }

    @Override
    public int getTier() {
        return 5;
    }

    @Override
    public String GUID() {
        return "ringwaterfire0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(
            new ElementalResistFlat(Elements.Water).size(StatMod.Size.DOUBLE),
            new ElementalResistFlat(Elements.Fire).size(StatMod.Size.DOUBLE)
        );
    }

    @Override
    public List<StatMod> primaryStats() {
        return Arrays.asList(
            new ElementalSpellDamageFlat(Elements.Fire).size(StatMod.Size.DOUBLE),
            new ElementalSpellDamageFlat(Elements.Water).size(StatMod.Size.DOUBLE));

    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Frostfire Ring";
    }

    @Override
    public String locDescForLangFile() {
        return "I will attain perfect control.";
    }
}
