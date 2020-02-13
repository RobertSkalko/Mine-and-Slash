package com.robertx22.mine_and_slash.database.stats.types.traits.low_dodge;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.multi.defense.ArmorMulti;
import com.robertx22.mine_and_slash.database.stats.types.traits.bases.BaseTraitLowDodge;

import java.util.Arrays;
import java.util.List;

public class LowDodgeAddArmor extends BaseTraitLowDodge {

    @Override
    public List<StatMod> getStats() {
        return Arrays.asList(new ArmorMulti());

    }

    @Override
    public String GUID() {
        return "low_dodge_add_armor";
    }

    @Override
    public String locNameForLangFile() {
        return "Armor on Low Dodge";
    }
}
