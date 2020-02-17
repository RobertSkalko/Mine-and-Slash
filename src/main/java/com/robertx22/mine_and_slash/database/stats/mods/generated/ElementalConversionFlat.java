package com.robertx22.mine_and_slash.database.stats.mods.generated;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.types.generated.ElementalConversion;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;
import com.robertx22.mine_and_slash.uncommon.interfaces.IGenerated;

import java.util.ArrayList;
import java.util.List;

public class ElementalConversionFlat extends StatMod implements IGenerated<StatMod> {

    public String BaseStatGUID;
    public Elements fromElement;
    public Elements toElement;

    public ElementalConversionFlat(Elements from, Elements to) {
        this.fromElement = from;
        this.toElement = to;

        ElementalConversion stat = new ElementalConversion(from, to);
        this.BaseStatGUID = stat.GUID();

    }

    @Override
    public float Min() {
        return 10;
    }

    @Override
    public float Max() {
        return 25;
    }

    @Override
    public StatModTypes getModType() {
        return StatModTypes.Flat;
    }

    @Override
    public Stat GetBaseStat() {
        return SlashRegistry.Stats().get(BaseStatGUID);
    }

    public List<StatMod> generateAllPossibleStatVariations() {

        List<StatMod> stats = new ArrayList<>();

        for (Elements from : Elements.getAllSingleElementals()) {
            if (from != Elements.Physical) {
                for (Elements to : Elements.getAllSingleElementals()) {
                    if (to != Elements.Physical && to != from) {
                        ElementalConversionFlat stat = new ElementalConversionFlat(from, to);
                        stats.add(stat);
                    }
                }
            }
        }
        return stats;
    }
}
