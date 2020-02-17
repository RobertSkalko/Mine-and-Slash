package com.robertx22.mine_and_slash.database.items.unique_items.charms;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.curios.Charm;
import com.robertx22.mine_and_slash.database.items.unique_items.IUnique;
import com.robertx22.mine_and_slash.database.items.unique_items.StatReq;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.HealthFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalPenePercent;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalResistFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalSpellDamageFlat;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;

import java.util.Arrays;
import java.util.List;

public class CharmNature implements IUnique {

    public CharmNature() {

    }

    static StatReq req = new StatReq(
        LvlPointStat.WISDOM, StatReq.Size.MEDIUM, LvlPointStat.STRENGTH, StatReq.Size.MEDIUM);

    @Override
    public StatReq getRequirements() {
        return req;
    }

    @Override
    public int Tier() {
        return 15;
    }

    @Override
    public String GUID() {
        return "charmnature0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new HealthFlat(), new ElementalPenePercent(Elements.Nature),
            new ElementalResistFlat(Elements.Nature).size(StatMod.Size.HIGH)
        );
    }

    @Override
    public GearItemSlot getGearSlot() {
        return Charm.INSTANCE;
    }

    @Override
    public List<StatMod> primaryStats() {
        return Arrays.asList(new ElementalSpellDamageFlat(Elements.Nature));
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Spirit Spring Charm";
    }

    @Override
    public String locDescForLangFile() {
        return "My life may be limited, but I shine throughout it.";
    }
}
