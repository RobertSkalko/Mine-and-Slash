package com.robertx22.mine_and_slash.database.unique_items.rings;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.DodgeFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.HealthFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalResistFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.WeaponDamageFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.percent.DodgePercent;
import com.robertx22.mine_and_slash.database.unique_items.bases.BaseUniqueRing;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;

import java.util.Arrays;
import java.util.List;

public class RingDodge extends BaseUniqueRing {

    public RingDodge() {

    }

    @Override
    public int Tier() {
        return 1;
    }

    @Override
    public String GUID() {
        return "ringdodge0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new DodgeFlat(), new DodgePercent(), new ElementalResistFlat(Elements.Nature), new HealthFlat(), new WeaponDamageFlat(WeaponTypes.Bow));
    }

    @Override
    public int getRarityRank() {
        return IRarity.Legendary;
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Ring of Swiftness";
    }

    @Override
    public String locDescForLangFile() {
        return "Swift as the Wind.";
    }
}
