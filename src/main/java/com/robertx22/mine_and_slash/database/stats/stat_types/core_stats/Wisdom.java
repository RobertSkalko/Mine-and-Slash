package com.robertx22.mine_and_slash.database.stats.stat_types.core_stats;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.EnergyRegenFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.HealthRegenFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.ManaRegenFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalResistFlat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

import java.util.Arrays;
import java.util.List;

public class Wisdom extends BaseCoreStat {
    @Override
    public int iconSpriteNumber() {
        return 14;
    }

    @Override
    public String locDescForLangFile() {
        return "Increases Both Mana-Health-Energy Regens and elemental resists";
    }

    @Override
    public String GUID() {
        return "Wisdom";
    }

    static float regenMulti = 0.3F;
    static float resistMulti = 0.1F;

    @Override
    public List<StatMod> statsThatBenefit() {
        return Arrays.asList(new ManaRegenFlat().multi(regenMulti), new EnergyRegenFlat().multi(regenMulti), new HealthRegenFlat()
                .multi(regenMulti), new ElementalResistFlat(Elements.Nature).multi(resistMulti), new ElementalResistFlat(Elements.Fire)
                .multi(resistMulti), new ElementalResistFlat(Elements.Thunder).multi(resistMulti), new ElementalResistFlat(Elements.Water)
                .multi(resistMulti)

        )

                ;
    }

    @Override
    public String locNameForLangFile() {
        return "Wisdom";
    }
}
