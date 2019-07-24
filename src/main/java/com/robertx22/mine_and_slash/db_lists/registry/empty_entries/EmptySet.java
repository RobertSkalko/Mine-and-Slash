package com.robertx22.mine_and_slash.db_lists.registry.empty_entries;

import com.robertx22.mine_and_slash.database.requirements.LevelRequirement;
import com.robertx22.mine_and_slash.database.requirements.Requirements;
import com.robertx22.mine_and_slash.database.sets.Set;
import com.robertx22.mine_and_slash.database.stats.StatMod;

import java.util.HashMap;

public class EmptySet extends Set {
    @Override
    public HashMap<Integer, StatMod> AllMods() {
        return new HashMap<>();
    }

    @Override
    public String locNameForLangFile() {
        return "";
    }

    @Override
    public String GUID() {
        return "";
    }

    @Override
    public Requirements requirements() {
        return new Requirements(LevelRequirement.fromLVL50());
    }
}
