package com.robertx22.mine_and_slash.database.unique_items.necklaces;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.curios.Necklace;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.EnergyRegenFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.HealthFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.HealthRegenFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.conversions.ManaToEnergyConvFlat;
import com.robertx22.mine_and_slash.database.unique_items.IUnique;
import com.robertx22.mine_and_slash.database.unique_items.StatReq;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;

import java.util.Arrays;
import java.util.List;

public class NecklaceEnergy implements IUnique {

    public NecklaceEnergy() {

    }

    static StatReq req = new StatReq(LvlPointStat.STAMINA, StatReq.Size.NORMAL);

    @Override
    public StatReq getRequirements() {
        return req;
    }

    @Override
    public GearItemSlot getGearSlot() {
        return Necklace.INSTANCE;
    }

    @Override
    public int Tier() {
        return 16;
    }

    @Override
    public String GUID() {
        return "necklaceenergy0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new ManaToEnergyConvFlat(), new EnergyRegenFlat().size(StatMod.Size.HIGH), new HealthRegenFlat());
    }

    @Override
    public List<StatMod> primaryStats() {
        return Arrays.asList(new HealthFlat());
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Inner Spirit Amulet";
    }

    @Override
    public String locDescForLangFile() {
        return "One becomes two, two becomes four!";
    }
}
