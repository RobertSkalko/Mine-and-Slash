package com.robertx22.mine_and_slash.database.affixes.prefixes.resource.rare_resource;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.ManaRegenFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.percent.ManaRegenPercent;

import java.util.Arrays;
import java.util.List;

public class DeepMind extends BaseRareResourcePrefix {

    @Override
    public String GUID() {
        return "Deep Mind";
    }

    @Override
    public List<StatMod> StatMods() {
        return Arrays.asList(new ManaRegenFlat(), new ManaRegenPercent());
    }

    @Override
    public String locNameForLangFile() {
        return "Deep Mind";
    }
}