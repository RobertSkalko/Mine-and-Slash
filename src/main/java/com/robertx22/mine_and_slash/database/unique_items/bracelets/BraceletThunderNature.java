package com.robertx22.mine_and_slash.database.unique_items.bracelets;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.curios.Bracelet;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.EnergyFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.HealthFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.ManaFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalSpellDamageFlat;
import com.robertx22.mine_and_slash.database.unique_items.IUnique;
import com.robertx22.mine_and_slash.database.unique_items.StatReq;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;

import java.util.Arrays;
import java.util.List;

public class BraceletThunderNature implements IUnique {

    public BraceletThunderNature() {

    }

    static StatReq req = new StatReq(
        LvlPointStat.STRENGTH, StatReq.Size.MEDIUM, LvlPointStat.VITALITY, StatReq.Size.MEDIUM);

    @Override
    public StatReq getRequirements() {
        return req;
    }

    @Override
    public int Tier() {
        return 16;
    }

    @Override
    public String GUID() {
        return "braceletthundernature0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(
            new ManaFlat().size(StatMod.Size.HIGH),
            new HealthFlat().size(StatMod.Size.HIGH),
            new EnergyFlat().size(StatMod.Size.HIGH)
        );
    }

    @Override
    public GearItemSlot getGearSlot() {
        return Bracelet.INSTANCE;
    }

    @Override
    public List<StatMod> primaryStats() {
        return Arrays.asList(
            new ElementalSpellDamageFlat(Elements.Nature).size(StatMod.Size.VERY_LOW),
            new ElementalSpellDamageFlat(Elements.Thunder).size(StatMod.Size.VERY_LOW)
        );
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Rooted Thunder Bracers";
    }

    @Override
    public String locDescForLangFile() {
        return "Heavenly Lightning? I call it mana.";
    }
}