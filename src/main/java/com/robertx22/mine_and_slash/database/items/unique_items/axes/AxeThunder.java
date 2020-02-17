package com.robertx22.mine_and_slash.database.items.unique_items.axes;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.weapons.Axe;
import com.robertx22.mine_and_slash.database.items.unique_items.IUnique;
import com.robertx22.mine_and_slash.database.items.unique_items.StatReq;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.CriticalHitFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalAttackDamageFlat;
import com.robertx22.mine_and_slash.database.stats.mods.percent.LifeOnHitPercent;
import com.robertx22.mine_and_slash.database.stats.mods.percent.offense.CriticalDamagePercent;
import com.robertx22.mine_and_slash.database.stats.mods.percent.offense.CriticalHitPercent;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;

import java.util.Arrays;
import java.util.List;

public class AxeThunder implements IUnique {
    public AxeThunder() {

    }

    static StatReq req = new StatReq(LvlPointStat.STRENGTH, StatReq.Size.MEDIUM);

    @Override
    public StatReq getRequirements() {
        return req;
    }

    @Override
    public int Tier() {
        return 11;
    }

    @Override
    public String GUID() {
        return "axethunder0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new CriticalHitPercent(), new CriticalHitFlat(), new CriticalDamagePercent(),
            new LifeOnHitPercent().size(StatMod.Size.LESS)
        );
    }

    @Override
    public List<StatMod> primaryStats() {
        return Arrays.asList(new ElementalAttackDamageFlat(Elements.Thunder));
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Thunderstorm Axe";
    }

    @Override
    public String locDescForLangFile() {
        return "Sparks fly, heads roll.";
    }

    @Override
    public GearItemSlot getGearSlot() {
        return Axe.INSTANCE;
    }
}
