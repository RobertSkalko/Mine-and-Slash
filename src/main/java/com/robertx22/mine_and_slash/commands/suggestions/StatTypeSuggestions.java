package com.robertx22.mine_and_slash.commands.suggestions;

import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StatTypeSuggestions extends CommandSuggestions {

    @Override
    public List<String> suggestions() {

        return Arrays.stream(StatModTypes.values())
            .map(x -> x.name())
            .collect(Collectors.toList());

    }

}

