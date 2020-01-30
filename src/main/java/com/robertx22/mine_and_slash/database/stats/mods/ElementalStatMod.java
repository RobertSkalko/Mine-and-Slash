package com.robertx22.mine_and_slash.database.stats.mods;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.types.ElementalStat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IElementalGenerated;
import com.robertx22.mine_and_slash.uncommon.wrappers.MapWrapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class ElementalStatMod<T extends ElementalStat> extends StatMod implements IElementalGenerated<StatMod> {

    public Elements element;

    public ElementalStatMod(Elements element) {
        this.element = element;

    }

    public abstract MapWrapper<Elements, T> getBaseStatMap();

    @Override
    public final Stat GetBaseStat() {
        return getBaseStatMap().get(element);
    }

    @Override
    public final List<StatMod> generateAllPossibleStatVariations() {

        List<StatMod> list = new ArrayList<>();

        HashMap<Elements, T> map = getBaseStatMap().MAP;

        Objects.requireNonNull(map, this.getClass().toString() + " stat mod's base stat map is null");

        for (Stat sg : map.values()) {
            list.add(this.newGeneratedInstance(sg.getElement()));
        }

        return list;

    }

    public List<StatMod> allSingleElementVariations() {
        return generateAllPossibleStatVariations().stream()
                .filter(x -> ((ElementalStatMod) x).element.isSingleElement)
                .collect(Collectors.toList());
    }

}
