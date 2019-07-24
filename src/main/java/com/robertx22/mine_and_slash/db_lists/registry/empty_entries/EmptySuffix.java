package com.robertx22.mine_and_slash.db_lists.registry.empty_entries;

import com.robertx22.mine_and_slash.database.affixes.Suffix;
import com.robertx22.mine_and_slash.database.requirements.LevelRequirement;
import com.robertx22.mine_and_slash.database.requirements.Requirements;
import com.robertx22.mine_and_slash.database.stats.StatMod;

import java.util.ArrayList;
import java.util.List;

public class EmptySuffix extends Suffix {
    @Override
    public String GUID() {
        return "";
    }

    @Override
    public List<StatMod> StatMods() {
        return new ArrayList<>();
    }

    @Override
    public Requirements requirements() {
        return new Requirements(LevelRequirement.fromLVL50());
    }

    @Override
    public String locNameForLangFile() {
        return "";
    }
}
