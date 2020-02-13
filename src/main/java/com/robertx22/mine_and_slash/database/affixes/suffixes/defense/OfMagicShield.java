package com.robertx22.mine_and_slash.database.affixes.suffixes.defense;

import com.robertx22.mine_and_slash.database.affixes.Suffix;
import com.robertx22.mine_and_slash.database.requirements.Requirements;
import com.robertx22.mine_and_slash.database.requirements.SlotRequirement;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.percent.MagicShieldPercent;

import java.util.Arrays;
import java.util.List;

public class OfMagicShield extends Suffix {

    public OfMagicShield() {
        super(new Requirements(SlotRequirement.clothArmorOnly()));
    }

    @Override
    public String GUID() {
        return "of_magic_shield";
    }

    @Override
    public List<StatMod> StatMods() {
        return Arrays.asList(new MagicShieldPercent());

    }

    @Override
    public String locNameForLangFile() {
        return "Of Magic Shield";
    }
}
