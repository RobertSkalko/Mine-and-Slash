package com.robertx22.mine_and_slash.database.items.unique_items.hammers;

import com.robertx22.mine_and_slash.database.items.unique_items.StatReq;
import com.robertx22.mine_and_slash.database.items.unique_items.bases.BaseUniqueHammer;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.CriticalHitFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalAttackDamageFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalSpellToAttackDMGFlat;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;

import java.util.Arrays;
import java.util.List;

public class HammerThunder extends BaseUniqueHammer {
    public HammerThunder() {

    }

    static StatReq req = new StatReq(LvlPointStat.STRENGTH, StatReq.Size.MEDIUM, LvlPointStat.STAMINA, StatReq.Size.MEDIUM);

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
        return "hammerthunder0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new ElementalSpellToAttackDMGFlat(Elements.Thunder), new CriticalHitFlat()
                .multi(4));
    }

    @Override
    public List<StatMod> primaryStats() {
        return Arrays.asList(new ElementalAttackDamageFlat(Elements.Thunder));
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Hammer of Thor";
    }

    @Override
    public String locDescForLangFile() {
        return "I will be safe when my enemies are dead.";
    }
}
