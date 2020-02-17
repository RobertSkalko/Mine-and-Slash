package com.robertx22.mine_and_slash.database.unique_items.pants;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.plate.PlatePants;
import com.robertx22.mine_and_slash.database.unique_items.IUnique;
import com.robertx22.mine_and_slash.database.unique_items.StatReq;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.corestats.CoreStatFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.HealthFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalResistFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalTransferFlat;
import com.robertx22.mine_and_slash.database.stats.types.core_stats.Strength;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;

import java.util.Arrays;
import java.util.List;

public class PantsNature implements IUnique {

    public PantsNature() {

    }

    static StatReq req = new StatReq(
        LvlPointStat.VITALITY, StatReq.Size.MEDIUM, LvlPointStat.INTELLIGENCE, StatReq.Size.SMALL);

    @Override
    public GearItemSlot getGearSlot() {
        return PlatePants.INSTANCE;
    }

    @Override
    public StatReq getRequirements() {
        return req;
    }

    @Override
    public int Tier() {
        return 14;
    }

    @Override
    public String GUID() {
        return "pantsnature0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new CoreStatFlat(Strength.INSTANCE).size(StatMod.Size.HIGH), new ElementalResistFlat(Elements.Nature).size(StatMod.Size.HIGH),
            new ElementalTransferFlat(Elements.Fire, Elements.Nature)
        );
    }

    @Override
    public List<StatMod> primaryStats() {
        return Arrays.asList(new HealthFlat());
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Rooted Leggings";
    }

    @Override
    public String locDescForLangFile() {
        return "Embrace my roots.";
    }
}
