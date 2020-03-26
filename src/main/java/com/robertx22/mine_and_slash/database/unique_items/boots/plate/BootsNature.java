package com.robertx22.mine_and_slash.database.unique_items.boots.plate;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.plate.PlateBoots;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.HealthFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.ManaRegenFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalResistFlat;
import com.robertx22.mine_and_slash.database.stats.mods.percent.HealthPercent;
import com.robertx22.mine_and_slash.database.unique_items.IUnique;
import com.robertx22.mine_and_slash.database.unique_items.StatReq;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;

import java.util.Arrays;
import java.util.List;

public class BootsNature implements IUnique {

    public BootsNature() {

    }

    static StatReq req = new StatReq(LvlPointStat.STAMINA, StatReq.Size.MEDIUM);

    @Override
    public StatReq getRequirements() {
        return req;
    }

    @Override
    public int Tier() {
        return 7;
    }

    @Override
    public String GUID() {
        return "bootsnature0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new HealthPercent().size(StatMod.Size.HIGH), new ManaRegenFlat().size(StatMod.Size.HIGH),
            new ElementalResistFlat(Elements.Nature)
        );

    }

    @Override
    public List<StatMod> primaryStats() {
        return Arrays.asList(new HealthFlat().size(StatMod.Size.HIGH));
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Tree Trunks";
    }

    @Override
    public String locDescForLangFile() {
        return "Nothing shall break my roots!";
    }

    @Override
    public GearItemSlot getGearSlot() {
        return PlateBoots.INSTANCE;
    }
}
