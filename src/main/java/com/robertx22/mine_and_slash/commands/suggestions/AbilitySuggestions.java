package com.robertx22.mine_and_slash.commands.suggestions;

import com.robertx22.mine_and_slash.registry.SlashRegistry;

import java.util.List;
import java.util.stream.Collectors;

public class AbilitySuggestions extends CommandSuggestions {

    @Override
    public List<String> suggestions() {

        List<String> list = SlashRegistry.Spells()
            .getList()
            .stream()
            .map(x -> x.GUID())
            .collect(Collectors.toList());

        SlashRegistry.Synergies()
            .getList()
            .forEach(x -> list.add(x.GUID()));

        return list;
    }

}
