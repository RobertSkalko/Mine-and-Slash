package com.robertx22.mine_and_slash.db_lists.registry.empty_entries;

import com.robertx22.mine_and_slash.database.runewords.RuneWord;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.items.runes.base.BaseRuneItem;

import java.util.ArrayList;
import java.util.List;

public class EmptyRuneWord extends RuneWord {
    @Override
    public List<StatMod> mods() {
        return new ArrayList<>();
    }

    @Override
    public String GUID() {
        return "";
    }

    @Override
    public List<BaseRuneItem> runes() {
        return new ArrayList<>();
    }

    @Override
    public String locNameForLangFile() {
        return "";
    }
}
