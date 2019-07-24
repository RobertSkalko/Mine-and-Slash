package com.robertx22.mine_and_slash.saveclasses.rune;

import com.robertx22.mine_and_slash.database.runewords.RuneWord;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;

@Storable
public class AwakenRuneWordData {

    @Store
    public String runeword;

    public RuneWord getWord() {
        return SlashRegistry.RuneWords().get(runeword);
    }
}
