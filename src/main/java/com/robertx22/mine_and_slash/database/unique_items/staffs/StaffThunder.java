package com.robertx22.mine_and_slash.database.unique_items.staffs;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.DodgeFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.offense.CriticalDamageFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.ManaOnHitFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalAttackDamageFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalPenePercent;
import com.robertx22.mine_and_slash.database.unique_items.bases.BaseUniqueStaff;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

import java.util.Arrays;
import java.util.List;

public class StaffThunder extends BaseUniqueStaff {

    public StaffThunder() {

    }

    @Override
    public int Tier() {
        return 5;
    }

    @Override
    public String GUID() {
        return "uniquestaffthunder0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new ElementalAttackDamageFlat(Elements.Thunder), new CriticalDamageFlat(), new ElementalPenePercent(Elements.Thunder), new DodgeFlat(), new ManaOnHitFlat());
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Thunderstorm Staff";
    }

    @Override
    public String locDescForLangFile() {
        return "Controlled power can bring both energy and destruction.";
    }
}
