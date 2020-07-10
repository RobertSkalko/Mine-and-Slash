package com.robertx22.mine_and_slash.database.affixes;

import com.robertx22.mine_and_slash.database.StatModifier;
import com.robertx22.mine_and_slash.database.requirements.Requirements;
import com.robertx22.mine_and_slash.database.requirements.bases.BaseRequirement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

public class GenericAffixBuilder<T> {

    List<T> elements = new ArrayList<>();

    int weight = 1000;
    Requirements requirements;
    List<AffixTag> tags = new ArrayList<>();
    BaseAffix.Type type;

    Function<T, String> guid;
    Function<T, List<StatModifier>> mods;

    HashMap<T, String> nameMap = new HashMap<>();

    public GenericAffixBuilder<T> guid(Function<T, String> guid) {
        this.guid = guid;
        return this;
    }

    public GenericAffixBuilder<T> add(T element, String name) {
        this.nameMap.put(element, name);
        this.elements.add(element);
        return this;
    }

    public GenericAffixBuilder<T> mods(Function<T, List<StatModifier>> mods) {
        this.mods = mods;
        return this;
    }

    public GenericAffixBuilder<T> Req(BaseRequirement... reqs) {
        requirements = new Requirements(reqs);
        return this;
    }

    public GenericAffixBuilder<T> Weight(int weight) {
        weight = weight;
        return this;
    }

    public GenericAffixBuilder<T> Tags(AffixTag... tags) {
        this.tags = Arrays.asList(tags);
        return this;
    }

    public GenericAffixBuilder<T> Prefix() {
        type = BaseAffix.Type.prefix;
        return this;
    }

    public GenericAffixBuilder<T> Suffix() {
        type = BaseAffix.Type.suffix;
        return this;
    }

    public GenericAffixBuilder<T> Implicit() {
        type = BaseAffix.Type.implicit;
        return this;
    }

    public void Build() {

        for (T element : elements) {
            BaseAffix affix = new BaseAffix();
            affix.guid = guid.apply(element);
            affix.mods = mods.apply(element);

            affix.type = type;
            affix.weight = weight;
            affix.langName = nameMap.get(element);
            affix.tags = tags;
            affix.requirements = requirements;

            affix.addToSerializables();
        }

    }

}
