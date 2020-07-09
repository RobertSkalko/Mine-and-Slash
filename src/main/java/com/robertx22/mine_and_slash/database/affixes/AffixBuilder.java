package com.robertx22.mine_and_slash.database.affixes;

import com.robertx22.mine_and_slash.database.StatModifier;
import com.robertx22.mine_and_slash.database.requirements.Requirements;
import com.robertx22.mine_and_slash.database.requirements.bases.BaseRequirement;

import java.util.Arrays;

public class AffixBuilder {

    BaseAffix affix = new BaseAffix();

    private AffixBuilder(String id) {
        affix.guid = id;
    }

    public static AffixBuilder Of(String id) {

        return new AffixBuilder(id);
    }

    public AffixBuilder Named(String name) {
        affix.langName = name;
        return this;
    }

    public AffixBuilder Req(BaseRequirement... reqs) {
        affix.requirements = new Requirements(reqs);
        return this;
    }

    public AffixBuilder Weight(int weight) {
        affix.weight = weight;
        return this;
    }

    public AffixBuilder Tags(AffixTag... tags) {
        affix.tags = Arrays.asList(tags);
        return this;
    }

    public AffixBuilder Stats(StatModifier... stats) {
        affix.mods = Arrays.asList(stats);
        return this;
    }

    public BaseAffix BuildPrefix() {
        affix.type = BaseAffix.Type.prefix;
        affix.addToSerializables();
        return affix;
    }

    public BaseAffix BuildSuffix() {
        affix.type = BaseAffix.Type.suffix;
        affix.addToSerializables();
        return affix;
    }

    public BaseAffix BuildImplicit() {
        affix.type = BaseAffix.Type.implicit;
        affix.addToSerializables();
        return affix;
    }

}
