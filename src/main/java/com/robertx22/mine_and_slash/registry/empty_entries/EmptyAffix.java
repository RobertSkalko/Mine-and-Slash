package com.robertx22.mine_and_slash.registry.empty_entries;

import com.robertx22.mine_and_slash.database.StatModifier;
import com.robertx22.mine_and_slash.database.affixes.BaseAffix;

import java.util.ArrayList;
import java.util.List;

public class EmptyAffix extends BaseAffix {

    private EmptyAffix() {
    }

    public static EmptyAffix getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public String GUID() {
        return "unknown_affix";
    }

    @Override
    public List<StatModifier> StatMods() {
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
