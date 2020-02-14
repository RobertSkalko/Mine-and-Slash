package com.robertx22.mine_and_slash.database.stats.types.resources.conversions;

import com.robertx22.mine_and_slash.database.stats.ConversionMethod;
import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.types.resources.EnergyRegen;
import com.robertx22.mine_and_slash.database.stats.types.resources.ManaRegen;
import com.robertx22.mine_and_slash.saveclasses.spells.StatScaling;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatConversion;

import java.util.Arrays;
import java.util.List;

public class ManaToEnergyConversion extends Stat implements IStatConversion {

    @Override
    public List<ConversionMethod> conversion() {
        return Arrays.asList(new ConversionMethod(ManaRegen.getInstance(), EnergyRegen.getInstance()));

    }

    @Override
    public String GUID() {
        return "mana_to_energy_conversion";
    }

    @Override
    public String locNameForLangFile() {
        return "Boost Mana Reg to Energy";
    }

    @Override
    public String locDescForLangFile() {
        return "Adds to 1 based on the others' amount";
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
}
