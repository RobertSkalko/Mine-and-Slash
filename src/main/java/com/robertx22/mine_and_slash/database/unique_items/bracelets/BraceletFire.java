package com.robertx22.mine_and_slash.database.unique_items.bracelets;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.curios.Bracelet;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalResistFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalSpellDamageFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalSpellToAttackDMGFlat;
import com.robertx22.mine_and_slash.database.stats.mods.percent.offense.CriticalDamagePercent;
import com.robertx22.mine_and_slash.database.unique_items.IUnique;
import com.robertx22.mine_and_slash.database.unique_items.StatReq;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;

import java.util.Arrays;
import java.util.List;

public class BraceletFire implements IUnique {

    public BraceletFire() {

    }

    static StatReq req = new StatReq(LvlPointStat.STRENGTH, StatReq.Size.SMALL);

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
        return "braceletfire0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(
            new ElementalSpellToAttackDMGFlat(Elements.Fire).size(StatMod.Size.HALF_MORE),
            new ElementalResistFlat(Elements.Fire).size(StatMod.Size.HALF_MORE),
            new CriticalDamagePercent().size(StatMod.Size.ONE_LESS));

    }

    @Override
    public List<StatMod> primaryStats() {
        return Arrays.asList(new ElementalSpellDamageFlat(Elements.Fire));
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Flaming Fists";
    }

    @Override
    public String locDescForLangFile() {
        return "Harness Fire, wield it as your weapon. Incinerate them.";
    }

    @Override
    public GearItemSlot getGearSlot() {
        return Bracelet.INSTANCE;
    }
}
