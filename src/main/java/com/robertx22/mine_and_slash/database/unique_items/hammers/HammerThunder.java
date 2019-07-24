package com.robertx22.mine_and_slash.database.unique_items.hammers;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.offense.CriticalHitFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalAttackDamageFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalSpellToAttackDMGFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.percent.much_less.CrippleLifeOnHitPercent;
import com.robertx22.mine_and_slash.database.stats.stat_mods.percent.much_less.CrippleManaOnHitPercent;
import com.robertx22.mine_and_slash.database.stats.stat_mods.percent.offense.CriticalDamagePercent;
import com.robertx22.mine_and_slash.database.stats.stat_mods.percent.offense.CriticalHitPercent;
import com.robertx22.mine_and_slash.database.unique_items.bases.BaseUniqueHammer;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

import java.util.Arrays;
import java.util.List;

public class HammerThunder extends BaseUniqueHammer {
    public HammerThunder() {

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
        return Arrays.asList(new ElementalAttackDamageFlat(Elements.Thunder), new ElementalSpellToAttackDMGFlat(Elements.Thunder), new CriticalHitPercent(), new CriticalDamagePercent(), new CriticalHitFlat(), new CrippleLifeOnHitPercent(), new CrippleManaOnHitPercent());
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
