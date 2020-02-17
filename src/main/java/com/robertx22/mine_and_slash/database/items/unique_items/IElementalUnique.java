package com.robertx22.mine_and_slash.database.items.unique_items;

import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IGenerated;

import java.util.ArrayList;
import java.util.List;

public interface IElementalUnique extends IGenerated<IUnique>, IUnique {

    IUnique newInstance(Elements element);

    @Override
    default List<IUnique> generateAllPossibleStatVariations() {
        List<IUnique> list = new ArrayList<>();
        Elements.getAllSingleElementals()
            .forEach(x -> list.add(newInstance(x)));
        return list;
    }
}
