package com.robertx22.mine_and_slash.database.affixes;

import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class GenericAffixBuilder<T> {

    List<T> elements = new ArrayList<>();

    Function<Elements, String> guid;

    public GenericAffixBuilder<T> guid(Function<Elements, String> guid) {
        this.guid = guid;
        return this;
    }

}
