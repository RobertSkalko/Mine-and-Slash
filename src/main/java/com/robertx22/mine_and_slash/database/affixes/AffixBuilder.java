package com.robertx22.mine_and_slash.database.affixes;

import com.robertx22.mine_and_slash.database.StatModifier;
import com.robertx22.mine_and_slash.database.requirements.Requirements;
import com.robertx22.mine_and_slash.database.requirements.bases.BaseRequirement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AffixBuilder {

    String guid;
    List<StatModifier> mods;
    String langName;

    int weight = 1000;
    Requirements requirements;
    public List<AffixTag> tags = new ArrayList<>();
    public BaseAffix.Type type;

    private AffixBuilder(String id) {
        this.guid = id;
    }

    public static AffixBuilder Normal(String id) {
        return new AffixBuilder(id);
    }

    public AffixBuilder Named(String name) {
        langName = name;
        return this;
    }

    public AffixBuilder Req(BaseRequirement... reqs) {
        requirements = new Requirements(reqs);
        return this;
    }

    public AffixBuilder Weight(int weight) {
        weight = weight;
        return this;
    }

    public AffixBuilder Tags(AffixTag... tags) {
        this.tags = Arrays.asList(tags);
        return this;
    }

    public AffixBuilder Stats(StatModifier... stats) {
        this.mods = Arrays.asList(stats);
        return this;
    }

    public AffixBuilder Prefix() {
        type = BaseAffix.Type.prefix;
        return this;
    }

    public AffixBuilder Suffix() {
        type = BaseAffix.Type.suffix;
        return this;
    }

    public AffixBuilder Implicit() {
        type = BaseAffix.Type.implicit;
        return this;
    }

    public void Build() {

        BaseAffix affix = new BaseAffix();
        affix.type = type;
        affix.requirements = requirements;
        affix.langName = langName;
        affix.weight = weight;
        affix.tags = tags;
        affix.mods = mods;
        affix.guid = guid;
        affix.addToSerializables();
    }

}
