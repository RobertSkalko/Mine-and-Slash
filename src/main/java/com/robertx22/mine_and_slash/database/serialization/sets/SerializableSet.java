package com.robertx22.mine_and_slash.database.serialization.sets;

import com.robertx22.mine_and_slash.database.requirements.Requirements;
import com.robertx22.mine_and_slash.database.sets.Set;
import com.robertx22.mine_and_slash.database.stats.StatMod;

import java.util.HashMap;

public class SerializableSet extends Set {

    Requirements req;
    HashMap<Integer, StatMod> mods;
    String GUID;
    String langNameId;

    public SerializableSet(HashMap<Integer, StatMod> mods, String GUID, String langNameId, Requirements req) {
        this.mods = mods;
        this.GUID = GUID;
        this.langNameId = langNameId;
        this.req = req;
    }

    @Override
    public HashMap<Integer, StatMod> AllMods() {
        return mods;
    }

    @Override
    public String locNameForLangFile() {
        return langNameId;
    }

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public Requirements requirements() {
        return req;
    }

}
