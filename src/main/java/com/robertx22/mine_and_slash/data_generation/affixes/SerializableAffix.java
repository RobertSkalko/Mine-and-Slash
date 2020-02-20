package com.robertx22.mine_and_slash.data_generation.affixes;

import com.robertx22.mine_and_slash.database.affixes.BaseAffix;
import com.robertx22.mine_and_slash.database.requirements.Requirements;
import com.robertx22.mine_and_slash.database.stats.StatMod;

import java.util.List;

public class SerializableAffix extends BaseAffix {

    String guid;
    List<StatMod> mods;
    String langName;
    int weight;
    int rarity;

    public SerializableAffix(int rarity, int weight, Requirements req, String guid, List<StatMod> mods, String langName,
                             BaseAffix.Type type) {
        super(req, type);
        this.guid = guid;
        this.mods = mods;
        this.langName = langName;
        this.weight = weight;
        this.rarity = rarity;
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
    public int getRarityRank() {
        return rarity;
    }

    @Override
    public String locNameForLangFile() {
        return "";
    }

    @Override
    public int Weight() {
        return weight;
    }

}
