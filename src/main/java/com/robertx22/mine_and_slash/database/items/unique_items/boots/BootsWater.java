package com.robertx22.mine_and_slash.database.items.unique_items.boots;

import com.robertx22.mine_and_slash.database.items.unique_items.StatReq;
import com.robertx22.mine_and_slash.database.items.unique_items.bases.BaseUniqueBoots;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.HealthFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalResistFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalSpellToAttackDMGFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.WeaponDamageFlat;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;

import java.util.Arrays;
import java.util.List;

public class BootsWater extends BaseUniqueBoots {

    public BootsWater() {

    }

    static StatReq req = new StatReq(LvlPointStat.WISDOM, StatReq.Size.MEDIUM);

    @Override
    public StatReq getRequirements() {
        return req;
    }

    @Override
    public int Tier() {
        return 18;
    }

    @Override
    public String GUID() {
        return "bootswater0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new WeaponDamageFlat(WeaponTypes.Sword), new ElementalSpellToAttackDMGFlat(Elements.Water)
                .multi(2.5F), new ElementalResistFlat(Elements.Water));

    }

    @Override
    public List<StatMod> primaryStats() {
        return Arrays.asList(new HealthFlat());
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Ice Steps";
    }

    @Override
    public String locDescForLangFile() {
        return "Ice forms wherever I walk.";
    }
}
