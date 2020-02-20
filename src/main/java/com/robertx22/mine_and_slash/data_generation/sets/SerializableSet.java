package com.robertx22.mine_and_slash.data_generation.sets;

import com.robertx22.mine_and_slash.database.requirements.Requirements;
import com.robertx22.mine_and_slash.database.sets.Set;
import com.robertx22.mine_and_slash.database.stats.StatMod;

import java.util.HashMap;

public class SerializableSet extends Set {

    Requirements req;
    HashMap<Integer, StatMod> mods;
    String GUID;
    String langNameId;
    int weight;
    int rarity;

    public SerializableSet(int rarity, int weight, HashMap<Integer, StatMod> mods, String GUID, String langNameId,
                           Requirements req) {
        this.mods = mods;
        this.GUID = GUID;
        this.langNameId = langNameId;
        this.req = req;
        this.rarity = rarity;
        this.weight = weight;
    }

    @Override
    public int getRarityRank() {
        return rarity;
    }

    @Override
    public HashMap<Integer, StatMod> AllMods() {
        return mods;
    }

    @Override
    public String locNameForLangFile() {
        return "";
    }

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public Requirements requirements() {
        return req;
    }

    @Override
    public String locNameLangFileGUID() {
        return langNameId;
    }

    @Override
    public int Weight() {
        return weight;
    }
}
