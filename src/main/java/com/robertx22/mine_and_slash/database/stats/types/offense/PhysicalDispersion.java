package com.robertx22.mine_and_slash.database.stats.types.offense;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.offense.PhysicalToHighestEle;
import com.robertx22.mine_and_slash.saveclasses.spells.StatScaling;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffects;

public class PhysicalDispersion extends Stat implements IStatEffects {

    public PhysicalDispersion() {
        this.maximumValue = 100;
    }

    public static String GUID = "physical_dispersion";

    @Override
    public String locDescForLangFile() {
        return "If you are doing elemental dmg, transfers physical damage to elemental damage of highest amount.";
    }

    @Override
    public StatGroup statGroup() {
        return StatGroup.Misc;
    }

    @Override
    public String GUID() {
        return GUID;
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
    public boolean IsPercent() {
        return true;
    }

    @Override
    public IStatEffect getEffect() {
        return PhysicalToHighestEle.INSTANCE;
    }

    @Override
    public String locNameForLangFile() {
        return "Physical Dispersion";
    }
}

