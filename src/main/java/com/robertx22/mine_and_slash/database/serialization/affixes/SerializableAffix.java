package com.robertx22.mine_and_slash.database.serialization.affixes;

import com.robertx22.mine_and_slash.database.affixes.BaseAffix;
import com.robertx22.mine_and_slash.database.requirements.Requirements;
import com.robertx22.mine_and_slash.database.stats.StatMod;

import java.util.List;

public class SerializableAffix extends BaseAffix {

    String guid;
    List<StatMod> mods;
    String langName;

    public SerializableAffix(Requirements req, String guid, List<StatMod> mods, String langName, BaseAffix.Type type) {
        super(req, type);
        this.guid = guid;
        this.mods = mods;
        this.langName = langName;
    }

    @Override
    public String GUID() {
        return guid;
    }

    @Override
    public List<StatMod> StatMods() {
        return mods;
    }

    @Override
    public String locNameLangFileGUID() {
        return langName;
    }

    @Override
    public String locNameForLangFile() {
        return "";
    }

}
