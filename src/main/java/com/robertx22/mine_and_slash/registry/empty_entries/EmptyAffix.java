package com.robertx22.mine_and_slash.registry.empty_entries;

import com.robertx22.mine_and_slash.database.affixes.Suffix;
import com.robertx22.mine_and_slash.database.requirements.LevelRequirement;
import com.robertx22.mine_and_slash.database.requirements.Requirements;
import com.robertx22.mine_and_slash.database.stats.StatMod;

import java.util.ArrayList;
import java.util.List;

public class EmptyAffix extends Suffix {

    private EmptyAffix() {
        super(new Requirements(LevelRequirement.fromLVL50()));
    }

    public static EmptyAffix getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public String GUID() {
        return "unknown_affix";
    }

    @Override
    public List<StatMod> StatMods() {
        return new ArrayList<>();
    }

    @Override
    public String locNameForLangFile() {
        return "Unknown Affix";
    }

    private static class SingletonHolder {
        private static final EmptyAffix INSTANCE = new EmptyAffix();
    }
}
