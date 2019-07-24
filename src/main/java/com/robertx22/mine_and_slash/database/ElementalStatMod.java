package com.robertx22.mine_and_slash.database;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IElementalGenerated;
import com.robertx22.mine_and_slash.uncommon.interfaces.IGenerated;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class ElementalStatMod extends StatMod implements IElementalGenerated<StatMod> {

    public Elements element;

    public ElementalStatMod(Elements element) {
        this.element = element;

    }

    @Override
    public List<StatMod> generateAllPossibleStatVariations() {

        List<StatMod> list = new ArrayList<>();

        IGenerated<Stat> gen = (IGenerated<Stat>) this.GetBaseStat();

        for (Stat stat : gen.generateAllPossibleStatVariations()) {
            list.add(this.newGeneratedInstance(stat.Element()));
        }

        return list;

    }

    public List<StatMod> allSingleElementVariations() {

        return generateAllPossibleStatVariations().stream()
                .filter(x -> ((ElementalStatMod) x).element.isSingleElement)
                .collect(Collectors.toList());
    }
}
