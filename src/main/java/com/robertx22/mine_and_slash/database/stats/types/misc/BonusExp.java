package com.robertx22.mine_and_slash.database.stats.types.misc;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.saveclasses.spells.StatScaling;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

public class BonusExp extends Stat {

    public static String GUID = "bonus_exp";

    @Override
    public String locDescForLangFile() {
        return "Increases exp gained";
    }

    @Override
    public boolean IsPercent() {
        return true;
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
    public String locNameForLangFile() {
        return "Bonus Exp";
    }
}
