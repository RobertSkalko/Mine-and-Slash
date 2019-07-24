package com.robertx22.mine_and_slash.database.affixes;

import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IElementalGenerated;

import java.util.ArrayList;
import java.util.List;

public abstract class ElementalSuffix extends Suffix implements IElementalGenerated<Suffix> {

    public Elements element;

    public ElementalSuffix(Elements element) {
        this.element = element;

    }

    public Elements Element() {
        return this.element;
    }

    public abstract Suffix newGeneratedInstance(Elements element);

    @Override
    public List<Suffix> generateAllPossibleStatVariations() {
        List<Suffix> list = new ArrayList<>();
        Elements.getAllSingleElements().forEach(x -> list.add(newGeneratedInstance(x)));
        return list;

    }

}

