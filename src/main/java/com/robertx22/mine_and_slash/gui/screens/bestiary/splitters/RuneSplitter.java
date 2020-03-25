package com.robertx22.mine_and_slash.gui.screens.bestiary.splitters;

import com.robertx22.mine_and_slash.database.runes.base.BaseRune;
import com.robertx22.mine_and_slash.gui.screens.bestiary.BestiaryEntry;
import com.robertx22.mine_and_slash.gui.screens.bestiary.groups.RuneBestiary;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RuneSplitter extends BaseSplitter<BaseRune> {

    public RuneSplitter() {
        super(new RuneBestiary());
    }

    @Override
    public List<BestiaryEntry> split(int lvl) {

        List<BestiaryEntry> entries = new ArrayList<>();

        List<BaseRune> runes = group.getAll(lvl);

        List<BaseRune> uniques = new ArrayList<>();
        List<BaseRune> normals = new ArrayList<>();

        uniques = runes.stream()
            .filter(x -> x.isUnique())
            .collect(Collectors.toList());
        normals = runes.stream()
            .filter(x -> !x.isUnique())
            .collect(Collectors.toList());

        entries.add(new BestiaryEntry.Splitter("Unique Runes: "));
        uniques.forEach(x -> entries.add(new BestiaryEntry.Item(group.createStack(lvl, x), group)));

        entries.add(new BestiaryEntry.Splitter("Normal Runes: "));
        normals.forEach(x -> entries.add(new BestiaryEntry.Item(group.createStack(lvl, x), group)));

        return entries;
    }
}
