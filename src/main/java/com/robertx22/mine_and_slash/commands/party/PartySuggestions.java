package com.robertx22.mine_and_slash.commands.party;

import com.robertx22.mine_and_slash.commands.suggestions.CommandSuggestions;

import java.util.ArrayList;
import java.util.List;

public class PartySuggestions extends CommandSuggestions {

    @Override
    public List<String> suggestions() {

        List<String> list = new ArrayList();
        list.add("join");
        list.add("join");
        list.add("join");

        return list;
    }

}

