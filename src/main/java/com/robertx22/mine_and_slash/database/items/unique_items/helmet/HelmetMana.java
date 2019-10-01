package com.robertx22.mine_and_slash.database.items.unique_items.helmet;

import com.robertx22.mine_and_slash.database.items.unique_items.StatReq;
import com.robertx22.mine_and_slash.database.items.unique_items.bases.BaseUniqueHelmet;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.HealthFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.MajorManaRegenFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.ManaFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.WeaponDamageFlat;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;

import java.util.Arrays;
import java.util.List;

public class HelmetMana extends BaseUniqueHelmet {

    public HelmetMana() {

    }

    static StatReq req = new StatReq(LvlPointStat.WISDOM, StatReq.Size.NORMAL);

    @Override
    public StatReq getRequirements() {
        return req;
    }

    @Override
    public int Tier() {
        return 17;
    }

    @Override
    public String GUID() {
        return "helmetmana0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new ManaFlat().multi(3), new MajorManaRegenFlat(), new WeaponDamageFlat(WeaponTypes.Staff));
    }

    @Override
    public List<StatMod> primaryStats() {
        return Arrays.asList(new HealthFlat());
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Helmet of Mana";
    }

    @Override
    public String locDescForLangFile() {
        return "Flow to me.";
    }

}