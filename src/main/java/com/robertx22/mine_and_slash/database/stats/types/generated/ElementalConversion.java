package com.robertx22.mine_and_slash.database.stats.types.generated;

import com.robertx22.mine_and_slash.database.stats.ConversionMethod;
import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.saveclasses.spells.StatScaling;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IGenerated;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatConversion;

import java.util.ArrayList;
import java.util.List;

public class ElementalConversion extends Stat implements IStatConversion, IGenerated<Stat> {

    List<ConversionMethod> conversion = new ArrayList<>();

    public ElementalConversion(Elements from, Elements to) {
        this.fromElement = from;
        this.toElement = to;
        this.maximumValue = 100;
        this.GUID = from.name() + "_to_" + to.name() + "_conversion";
        this.GUID = GUID.toLowerCase();

        conversion.add(new ConversionMethod(new ElementalAttackDamage(from), new ElementalAttackDamage(to)));
        conversion.add(new ConversionMethod(new ElementalSpellDamage(from), new ElementalSpellDamage(to)));

    }

    @Override
    public boolean IsShownOnStatGui() {
        return false;
    }

    public String GUID;
    public Elements fromElement;
    public Elements toElement;

    @Override
    public List<ConversionMethod> conversion() {
        return conversion;

    }

    public List<Stat> generateAllPossibleStatVariations() {

        List<Stat> stats = new ArrayList<>();

        for (Elements from : Elements.getAllSingleElementals()) {
            if (from != Elements.Physical) {
                for (Elements to : Elements.getAllSingleElementals()) {
                    if (to != Elements.Physical && to != from) {
                        ElementalConversion stat = new ElementalConversion(from, to);
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
    public String locDescForLangFile() {
        return "Conversion adds a percent of one to the other";
    }

    @Override
    public String locDescLangFileGUID() {
        return Ref.MODID + ".stat_desc." + "elemental_conversion";
    }

    @Override
    public boolean IsPercent() {
        return true;
    }

    @Override
    public StatScaling getScaling() {
        return StatScaling.NONE;
    }

    @Override
    public Elements getElement() {
        return null;
    }

    @Override
    public String locNameForLangFile() {
        return "Boost " + fromElement.name() + " to " + toElement.name();
    }

}
