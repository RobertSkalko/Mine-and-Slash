package com.robertx22.mine_and_slash.database.stats.mods.generated;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.types.generated.ElementalTransfer;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;
import com.robertx22.mine_and_slash.uncommon.interfaces.IGenerated;

import java.util.ArrayList;
import java.util.List;

public class ElementalTransferFlat extends StatMod implements IGenerated<StatMod> {

    public String BaseStatGUID;
    public Elements fromElement;
    public Elements toElement;

    public ElementalTransferFlat(Elements from, Elements to) {
        this.fromElement = from;
        this.toElement = to;

        ElementalTransfer stat = new ElementalTransfer(from, to);
        this.BaseStatGUID = stat.GUID();

    }

    @Override
    public float Min() {
        return 25;
    }

    @Override
    public float Max() {
        return 55;
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
                        ElementalTransferFlat stat = new ElementalTransferFlat(from, to);
                        stats.add(stat);
                    }
                }
            }
        }
        return stats;
    }
}
