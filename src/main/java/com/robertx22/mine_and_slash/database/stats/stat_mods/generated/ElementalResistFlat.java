package com.robertx22.mine_and_slash.database.stats.stat_mods.generated;

import com.robertx22.mine_and_slash.database.ElementalStatMod;
import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_types.generated.ElementalResist;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;

public class ElementalResistFlat extends ElementalStatMod {

    public ElementalResistFlat(Elements element) {
        super(element);
    }

    @Override
    public StatMod newGeneratedInstance(Elements element) {
        return new ElementalResistFlat(element);
    }

    @Override
    public Stat GetBaseStat() {
        return new ElementalResist(this.element);
    }

    @Override
    public float Min() {
        return 4;
    }

    @Override
    public float Max() {
        return 12;
    }

    @Override
    public StatTypes Type() {
        return StatTypes.Flat;
    }

    @Override
    public String GUID() {
        return element.name() + "ResistFlat";
    }
}
