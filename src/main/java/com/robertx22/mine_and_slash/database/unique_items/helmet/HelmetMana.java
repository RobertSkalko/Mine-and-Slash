package com.robertx22.mine_and_slash.database.unique_items.helmet;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.ArmorFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.MajorDodgeFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.HealthFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.MajorManaRegenFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.ManaFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.WeaponDamageFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.percent.ManaRegenPercent;
import com.robertx22.mine_and_slash.database.unique_items.bases.BaseUniqueHelmet;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;

import java.util.Arrays;
import java.util.List;

public class HelmetMana extends BaseUniqueHelmet {

    public HelmetMana() {

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
        return Arrays.asList(new HealthFlat(), new ManaFlat(), new MajorManaRegenFlat(), new ManaRegenPercent(), new MajorDodgeFlat(), new ArmorFlat(), new WeaponDamageFlat(WeaponTypes.Staff));
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