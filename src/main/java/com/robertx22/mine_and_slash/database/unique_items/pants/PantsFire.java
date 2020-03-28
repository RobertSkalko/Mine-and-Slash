package com.robertx22.mine_and_slash.database.unique_items.pants;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.plate.PlatePants;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.defense.ArmorFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalAffinityFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalSpellDamagePercent;
import com.robertx22.mine_and_slash.database.stats.mods.multi.defense.ArmorMulti;
import com.robertx22.mine_and_slash.database.unique_items.IUnique;
import com.robertx22.mine_and_slash.database.unique_items.StatReq;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;

import java.util.Arrays;
import java.util.List;

public class PantsFire implements IUnique {

    public PantsFire() {

    }

    static StatReq req = new StatReq(
        LvlPointStat.STAMINA, StatReq.Size.NORMAL, LvlPointStat.INTELLIGENCE, StatReq.Size.SMALL);

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
        return 15;
    }

    @Override
    public String GUID() {
        return "pantsfire0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(
            new ArmorFlat().size(StatMod.Size.HALF_MORE),
            new ArmorMulti(),
            new ElementalSpellDamagePercent(Elements.Fire)
        );
    }

    @Override
    public List<StatMod> primaryStats() {
        return Arrays.asList(new ElementalAffinityFlat(Elements.Fire));
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Flaming Leggings";
    }

    @Override
    public String locDescForLangFile() {
        return "Embrace my flames.";
    }
}
