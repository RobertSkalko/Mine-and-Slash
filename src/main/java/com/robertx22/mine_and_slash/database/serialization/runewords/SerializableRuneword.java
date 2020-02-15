package com.robertx22.mine_and_slash.database.serialization.runewords;

import com.robertx22.mine_and_slash.database.items.runes.base.BaseRuneItem;
import com.robertx22.mine_and_slash.database.runewords.RuneWord;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;

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
    public List<BaseRuneItem> runes() {
        return runes.stream().map(x -> getRune(x)).collect(Collectors.toList());
    }

    @Override
    public String locNameLangFileGUID() {
        return langName;
    }

    @Override
    public String locNameForLangFile() {
        return "";
    }

    BaseRuneItem getRune(String str) {
        if (SlashRegistry.Runes().isRegistered(str)) {
            return SlashRegistry.Runes().get(str);
        } else if (SlashRegistry.UniqueRunes().isRegistered(str)) {
            return SlashRegistry.UniqueRunes().get(str);
        }
        return null;

    }

}
