package com.robertx22.mine_and_slash.database.sets;

import com.robertx22.mine_and_slash.uncommon.interfaces.IGenerated;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

import java.util.ArrayList;
import java.util.List;

public abstract class ElementalSet extends Set implements IGenerated<Set> {

    public Elements element;

    public ElementalSet(Elements element) {
        this.element = element;
    }

    public abstract Set newGeneratedInstance(Elements element);

    @Override
    public List<Set> generateAllPossibleStatVariations() {
        List<Set> list = new ArrayList<>();
        Elements.getAllSingleElements().forEach(x -> list.add(newGeneratedInstance(x)));
        return list;
    }
}
