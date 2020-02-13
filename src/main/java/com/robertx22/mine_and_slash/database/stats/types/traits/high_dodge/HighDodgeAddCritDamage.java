package com.robertx22.mine_and_slash.database.stats.types.traits.high_dodge;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.CriticalDamageFlat;
import com.robertx22.mine_and_slash.database.stats.types.traits.bases.BaseTraitHighDodge;

import java.util.Arrays;
import java.util.List;

public class HighDodgeAddCritDamage extends BaseTraitHighDodge {

    @Override
    public List<StatMod> getStats() {
        return Arrays.asList(new CriticalDamageFlat());

    }

    @Override
    public String GUID() {
        return "high_dodge_add_crit_camage";
    }

    @Override
    public String locNameForLangFile() {
        return "Crit Damage on High Dodge";
    }
}
