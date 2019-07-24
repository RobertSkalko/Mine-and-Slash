package com.robertx22.mine_and_slash.database.unique_items.chest;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.EnergyRegenFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.HealthFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.ManaFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.conversions.EnergyToManaConvFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.WeaponDamageFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.percent.much_less.CrippleLifeOnHitPercent;
import com.robertx22.mine_and_slash.database.unique_items.bases.BaseUniqueChest;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;

import java.util.Arrays;
import java.util.List;

public class ChestMana extends BaseUniqueChest {

    public ChestMana() {

    }

    @Override
    public int Tier() {
        return 11;

    }

    @Override
    public String GUID() {
        return "chestmana0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new EnergyToManaConvFlat(), new HealthFlat(), new ManaFlat(), new EnergyRegenFlat(), new WeaponDamageFlat(WeaponTypes.Staff), new CrippleLifeOnHitPercent());
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Shirt of the Arcane";
    }

    @Override
    public String locDescForLangFile() {
        return "The process of multiplying energy has just begun!";
    }
}