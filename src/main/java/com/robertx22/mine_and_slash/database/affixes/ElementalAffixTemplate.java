package com.robertx22.mine_and_slash.database.affixes;

import com.robertx22.mine_and_slash.database.StatModifier;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

import java.util.List;

public abstract class ElementalAffixTemplate {

    public abstract String GUID(Elements element);

    public abstract List<StatModifier> Stats(Elements element);

    public abstract String FireName();

    public abstract String WaterName();

    public abstract String NatureName();

    public abstract String ThunderName();

    public String getName(Elements element) {

        if (element == Elements.Fire) {
            return FireName();
        }
        if (element == Elements.Water) {
            return WaterName();
        }
        if (element == Elements.Thunder) {
            return ThunderName();
        }
        if (element == Elements.Nature) {
            return NatureName();
        }
        return null;
    }

}
