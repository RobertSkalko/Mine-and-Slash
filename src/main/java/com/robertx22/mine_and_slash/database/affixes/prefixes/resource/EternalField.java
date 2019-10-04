package com.robertx22.mine_and_slash.database.affixes.prefixes.resource;

import com.robertx22.mine_and_slash.database.affixes.Prefix;
import com.robertx22.mine_and_slash.database.requirements.Requirements;
import com.robertx22.mine_and_slash.database.requirements.SlotRequirement;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.MagicShieldRegenFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.percent.MagicShieldPercent;

import java.util.Arrays;
import java.util.List;

public class EternalField extends Prefix {

    public EternalField() {
        super(new Requirements(SlotRequirement.ring()));
    }

    @Override
    public String GUID() {
        return "EternalField";
    }

    @Override
    public List<StatMod> StatMods() {
        return Arrays.asList(new MagicShieldRegenFlat(), new MagicShieldPercent());
    }

    @Override
    public String locNameForLangFile() {
        return "Eternal  Field";
    }
}
