package com.robertx22.mine_and_slash.database.unique_items.rings;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.curios.Ring;
import com.robertx22.mine_and_slash.database.unique_items.IUnique;
import com.robertx22.mine_and_slash.database.unique_items.StatReq;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.SpellDamageFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.EnergyRegenFlat;
import com.robertx22.mine_and_slash.database.stats.mods.percent.EnergyRegenPercent;
import com.robertx22.mine_and_slash.database.stats.mods.percent.offense.CriticalDamagePercent;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;

import java.util.Arrays;
import java.util.List;

public class RingEnergy implements IUnique {

    public RingEnergy() {

    }

    static StatReq req = new StatReq(LvlPointStat.STAMINA, StatReq.Size.NORMAL);

    @Override
    public StatReq getRequirements() {
        return req;
    }

    @Override
    public GearItemSlot getGearSlot() {
        return Ring.INSTANCE;
    }

    @Override
    public int Tier() {
        return 15;
    }

    @Override
    public String GUID() {
        return "ringenergy0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(
            new EnergyRegenFlat().size(StatMod.Size.HIGH), new EnergyRegenPercent().size(StatMod.Size.HIGH),
            new CriticalDamagePercent().size(StatMod.Size.MUCH_LESS)
        );
    }

    @Override
    public List<StatMod> primaryStats() {
        return Arrays.asList(new SpellDamageFlat());
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Ring of Unlimited Endurance";
    }

    @Override
    public String locDescForLangFile() {
        return "I will pay any price to continue!";
    }
}
