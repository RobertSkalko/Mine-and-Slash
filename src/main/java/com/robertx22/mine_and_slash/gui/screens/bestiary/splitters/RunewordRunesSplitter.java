package com.robertx22.mine_and_slash.gui.screens.bestiary.splitters;

import com.robertx22.mine_and_slash.database.runewords.RuneWord;
import com.robertx22.mine_and_slash.gui.screens.bestiary.BestiaryEntry;
import com.robertx22.mine_and_slash.gui.screens.bestiary.groups.RuneWordBestiary;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class RunewordRunesSplitter extends BaseSplitter<RuneWord> {

    public RunewordRunesSplitter() {
        super(new RuneWordBestiary());
    }

    @Override
    public List<BestiaryEntry> split(int lvl) {

        List<BestiaryEntry> entries = new ArrayList<>();

        List<RuneWord> runewords = group.getAll(lvl);

        Function<RuneWord, Integer> bySize = x -> x.size();

        Map<Integer, List<RuneWord>> collect = runewords.stream()
            .collect(Collectors.groupingBy(bySize));

        collect.entrySet()
            .stream()
            .sorted(Comparator.comparingInt(x -> x.getKey()));

        collect.entrySet()
            .forEach(entry -> {
                entries.add(new BestiaryEntry.Splitter(entry.getKey() + " Runes: "));
                entry.getValue()
                    .forEach(r -> entries.add(new BestiaryEntry.Item(group.createStack(lvl, r), group)));

            });

        return entries;
    }
}

