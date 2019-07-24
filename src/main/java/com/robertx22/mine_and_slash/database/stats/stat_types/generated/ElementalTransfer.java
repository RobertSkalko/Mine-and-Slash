package com.robertx22.mine_and_slash.database.stats.stat_types.generated;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.TransferMethod;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IGenerated;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatTransfer;

import java.util.ArrayList;
import java.util.List;

public class ElementalTransfer extends Stat implements IStatTransfer, IGenerated<Stat> {

    @Override
    public String locDescForLangFile() {
        return "Transfer takes a % from 1 and gives to the other";
    }

    @Override
    public String locDescLangFileGUID() {
        return Ref.MODID + ".stat_desc." + "elemental_transfer";
    }

    @Override
    public boolean IsShownOnTooltip() {
        return false;
    }

    @Override
    public boolean IsPercent() {
        return true;
    }

    @Override
    public boolean ScalesToLevel() {
        return false;
    }

    @Override
    public Elements Element() {
        return null;
    }

    List<TransferMethod> transfers = new ArrayList<>();

    public ElementalTransfer(Elements from, Elements to) {
        this.fromElement = from;
        this.toElement = to;
        this.maximumValue = 100;
        this.GUID = from.name() + "_to_" + to.name() + "_transfer";
        this.GUID = GUID.toLowerCase();

        transfers.add(new TransferMethod(new ElementalAttackDamage(from), new ElementalAttackDamage(to)));
        transfers.add(new TransferMethod(new ElementalSpellDamage(from), new ElementalSpellDamage(to)));

    }

    public String GUID;
    public Elements fromElement;
    public Elements toElement;

    @Override
    public List<TransferMethod> Transfer() {
        return transfers;

    }

    public List<Stat> generateAllPossibleStatVariations() {

        List<Stat> stats = new ArrayList<>();

        for (Elements from : Elements.getAllSingleElements()) {
            if (from != Elements.Physical) {
                for (Elements to : Elements.getAllSingleElements()) {
                    if (to != Elements.Physical && to != from) {
                        ElementalTransfer stat = new ElementalTransfer(from, to);
                        stats.add(stat);
                    }
                }
            }
        }
        return stats;
    }

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public String locNameForLangFile() {
        return "Transfer " + fromElement.name() + " to " + toElement.name();
    }

}
