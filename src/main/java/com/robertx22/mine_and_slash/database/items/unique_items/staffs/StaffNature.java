package com.robertx22.mine_and_slash.database.items.unique_items.staffs;

import com.robertx22.mine_and_slash.database.items.unique_items.bases.BaseUniqueStaff;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.HealthRegenFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalAttackDamageFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalPeneFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalPenePercent;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;

import java.util.Arrays;
import java.util.List;

public class StaffNature extends BaseUniqueStaff {

    public StaffNature() {

    }

    @Override
    public int Tier() {
        return 5;
    }

    @Override
    public String GUID() {
        return "uniquestaffnature0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new ElementalPeneFlat(Elements.Nature), new ElementalPenePercent(Elements.Nature), new HealthRegenFlat());
    }

    @Override
    public List<StatMod> primaryStats() {
        return Arrays.asList(new ElementalAttackDamageFlat(Elements.Nature));
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Worldbreaker Staff";
    }

    @Override
    public String locDescForLangFile() {
        return "Won't break? Smash harder!";
    }
}
