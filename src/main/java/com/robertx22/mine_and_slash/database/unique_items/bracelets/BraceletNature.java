package com.robertx22.mine_and_slash.database.unique_items.bracelets;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.curios.Bracelet;
import com.robertx22.mine_and_slash.database.unique_items.IUnique;
import com.robertx22.mine_and_slash.database.unique_items.StatReq;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.HealthFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalResistFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalSpellDamageFlat;
import com.robertx22.mine_and_slash.database.stats.mods.percent.HealthRegenPercent;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;

import java.util.Arrays;
import java.util.List;

public class BraceletNature implements IUnique {

    public BraceletNature() {

    }

    static StatReq req = new StatReq(LvlPointStat.VITALITY, StatReq.Size.MEDIUM);

    @Override
    public StatReq getRequirements() {
        return req;
    }

    @Override
    public int Tier() {
        return 2;
    }

    @Override
    public String GUID() {
        return "braceletnature0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new ElementalResistFlat(Elements.Nature).size(StatMod.Size.HIGH), new HealthFlat(), new HealthRegenPercent());
    }

    @Override
    public GearItemSlot getGearSlot() {
        return Bracelet.INSTANCE;
    }

    @Override
    public List<StatMod> primaryStats() {
        return Arrays.asList(new ElementalSpellDamageFlat(Elements.Nature));
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Thorn Bracers";
    }

    @Override
    public String locDescForLangFile() {
        return "Fools fight for treasure, but I keep on living.";
    }
}
