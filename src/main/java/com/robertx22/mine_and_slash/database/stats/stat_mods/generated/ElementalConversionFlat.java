package com.robertx22.mine_and_slash.database.stats.stat_mods.generated;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_types.generated.ElementalConversion;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;
import com.robertx22.mine_and_slash.uncommon.interfaces.IGenerated;

import java.util.ArrayList;
import java.util.List;

public class ElementalConversionFlat extends StatMod implements IGenerated<StatMod> {

    public String GUID;
    public String BaseStatGUID;
    public Elements fromElement;
    public Elements toElement;

    public ElementalConversionFlat(Elements from, Elements to) {
        this.fromElement = from;
        this.toElement = to;
        this.GUID = from.name() + "To" + to.name() + "ConvFlat";

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
    public StatTypes Type() {
        return StatTypes.Flat;
    }

    @Override
    public Stat GetBaseStat() {
        return SlashRegistry.Stats().get(BaseStatGUID);
    }

    @Override
    public String GUID() {
        return GUID;
    }

    public List<StatMod> generateAllPossibleStatVariations() {

        List<StatMod> stats = new ArrayList<>();

        for (Elements from : Elements.getAllSingleElements()) {
            if (from != Elements.Physical) {
                for (Elements to : Elements.getAllSingleElements()) {
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
