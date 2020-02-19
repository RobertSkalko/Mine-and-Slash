package com.robertx22.mine_and_slash.data_packs.runewords;

import com.robertx22.mine_and_slash.database.runes.base.BaseRune;
import com.robertx22.mine_and_slash.database.runewords.RuneWord;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.registry.SlashRegistry;

import java.util.List;
import java.util.stream.Collectors;

public class SerializableRuneword extends RuneWord {

    String guid;
    List<StatMod> mods;
    String langName;
    int weight;
    List<String> runes;
    int rarity;

    public SerializableRuneword(int rarity, int weight, String guid, List<StatMod> mods, String langName,
                                List<String> runes) {
        this.guid = guid;
        this.mods = mods;
        this.langName = langName;
        this.weight = weight;
        this.runes = runes;
        this.rarity = rarity;
    }

    @Override
    public List<StatMod> mods() {
        return mods;
    }

    @Override
    public String GUID() {
        return guid;
    }

    @Override
    public int getRarityRank() {
        return rarity;
    }

    @Override
    public List<BaseRune> runes() {
        return runes.stream()
            .map(x -> getRune(x))
            .collect(Collectors.toList());
    }

    @Override
    public String locNameLangFileGUID() {
        return langName;
    }

    @Override
    public String locNameForLangFile() {
        return "";
    }

    BaseRune getRune(String str) {
        if (SlashRegistry.Runes()
            .isRegistered(str)) {
            return SlashRegistry.Runes()
                .get(str);
        }
        return null;

    }

}
