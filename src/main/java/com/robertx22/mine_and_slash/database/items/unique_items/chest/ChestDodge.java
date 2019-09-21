package com.robertx22.mine_and_slash.database.items.unique_items.chest;

import com.robertx22.mine_and_slash.database.items.unique_items.StatReq;
import com.robertx22.mine_and_slash.database.items.unique_items.bases.BaseUniqueChest;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.MajorDodgeFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.HealthFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalResistFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.WeaponDamageFlat;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;

import java.util.Arrays;
import java.util.List;

public class ChestDodge extends BaseUniqueChest {

    public ChestDodge() {

    }

    static StatReq req = new StatReq(LvlPointStat.DEXTERITY, StatReq.Size.BIG);

    @Override
    public StatReq getRequirements() {
        return req;
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
        return Arrays.asList(new MajorDodgeFlat().multi(1.2F), new WeaponDamageFlat(WeaponTypes.Bow), new ElementalResistFlat(Elements.Nature));
    }

    @Override
    public List<StatMod> primaryStats() {
        return Arrays.asList(new HealthFlat());
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