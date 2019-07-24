package com.robertx22.mine_and_slash.database.unique_items.staffs;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.offense.PhysicalDamageFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.HealthRegenFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.LifeOnHitFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.LifestealFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.percent.LifestealPercent;
import com.robertx22.mine_and_slash.database.stats.stat_mods.percent.much_less.CrippleManaOnHitPercent;
import com.robertx22.mine_and_slash.database.unique_items.bases.BaseUniqueStaff;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;

import java.util.Arrays;
import java.util.List;

public class StaffLifesteal extends BaseUniqueStaff {

    public StaffLifesteal() {

    }

    @Override
    public int Tier() {
        return 10;
    }

    @Override
    public String GUID() {
        return "uniquestafflifesteal0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new PhysicalDamageFlat(), new LifestealPercent(), new LifestealFlat(), new LifeOnHitFlat(), new HealthRegenFlat(), new CrippleManaOnHitPercent());
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Vampire Staff";
    }

    @Override
    public String locDescForLangFile() {
        return "All blood will be mine, eventually.";
    }
}