package com.robertx22.mine_and_slash.database.unique_items.chest;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.MajorDodgeFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.HealthFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalResistFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.WeaponDamageFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.percent.DodgePercent;
import com.robertx22.mine_and_slash.database.stats.stat_mods.percent.much_less.CrippleLifeOnHitPercent;
import com.robertx22.mine_and_slash.database.unique_items.bases.BaseUniqueChest;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

import java.util.Arrays;
import java.util.List;

public class ChestDodge extends BaseUniqueChest {

    public ChestDodge() {

    }

    @Override
    public int Tier() {
        return 14;

    }

    @Override
    public String GUID() {
        return "chestdodge0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new HealthFlat(), new MajorDodgeFlat(), new DodgePercent(), new WeaponDamageFlat(WeaponTypes.Bow), new ElementalResistFlat(Elements.Nature), new CrippleLifeOnHitPercent());
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Thief's Chestplate";
    }

    @Override
    public String locDescForLangFile() {
        return "Come on, hit me!";
    }
}