package com.robertx22.mine_and_slash.database.unique_items.hammers;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.weapons.Hammer;
import com.robertx22.mine_and_slash.database.unique_items.IUnique;
import com.robertx22.mine_and_slash.database.unique_items.StatReq;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.PhysicalDamageFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalPeneFlat;
import com.robertx22.mine_and_slash.database.stats.mods.percent.offense.CriticalDamagePercent;
import com.robertx22.mine_and_slash.database.stats.mods.percent.offense.CriticalHitPercent;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;

import java.util.Arrays;
import java.util.List;

public class HammerPhysical implements IUnique {
    public HammerPhysical() {

    }

    static StatReq req = new StatReq(LvlPointStat.STRENGTH, StatReq.Size.NORMAL);

    @Override
    public StatReq getRequirements() {
        return req;
    }

    @Override
    public GearItemSlot getGearSlot() {
        return Hammer.INSTANCE;
    }

    @Override
    public int Tier() {
        return 8;
    }

    @Override
    public String GUID() {
        return "hammerphysical0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new ElementalPeneFlat(Elements.Physical), new CriticalHitPercent(), new CriticalDamagePercent());
    }

    @Override
    public List<StatMod> primaryStats() {
        return Arrays.asList(new PhysicalDamageFlat());
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Mountain Breaker";
    }

    @Override
    public String locDescForLangFile() {
        return "Not even mountains can bar my path!";
    }

}