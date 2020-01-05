package com.robertx22.mine_and_slash.commands.bases;

import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;

import java.util.List;
import java.util.stream.Collectors;

public class CrateSuggestions extends CommandSuggestions {

    @Override
    public List<String> suggestions() {
        return SlashRegistry.LootCrates()
                .getList()
                .stream()
                .map(x -> x.GUID())
                .collect(Collectors.toList());
    }

}
