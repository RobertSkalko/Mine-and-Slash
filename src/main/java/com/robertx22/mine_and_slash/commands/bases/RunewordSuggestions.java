package com.robertx22.mine_and_slash.commands.bases;

import com.robertx22.mine_and_slash.database.runewords.RuneWord;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;

import java.util.ArrayList;
import java.util.List;

public class RunewordSuggestions extends CommandSuggestions {

    @Override
    public List<String> suggestions() {

        List<String> list = new ArrayList();
        for (RuneWord item : SlashRegistry.RuneWords().getAll().values()) {
            list.add(item.GUID());
        }

        return list;
    }

}

