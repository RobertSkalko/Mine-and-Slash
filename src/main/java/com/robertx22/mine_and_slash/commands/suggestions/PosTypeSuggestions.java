package com.robertx22.mine_and_slash.commands.suggestions;

import com.robertx22.mine_and_slash.commands.dev.unique_dungeon.DunExportData;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PosTypeSuggestions extends CommandSuggestions {

    @Override
    public List<String> suggestions() {
        return Arrays.stream(DunExportData.PosType.values())
            .map(x -> x.name())
            .collect(Collectors.toList());
    }

}

