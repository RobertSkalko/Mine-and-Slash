package com.robertx22.mine_and_slash.database.stats.types.offense;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

public class PhysicalDamage extends Stat {

    public static Stat INSTANCE = new PhysicalDamage();

    public static String GUID = "Physical Damage";

    @Override
    public StatGroup statGroup() {
        return StatGroup.Damage;
    }

    @Override
    public String getIconPath() {
        return "phys_dmg";
    }

    @Override
    public String locDescForLangFile() {
        return "How much DMG your basic attacks do";
    }

    private PhysicalDamage() {
        this.BaseFlat = 1;
        this.minimumValue = 0;
    }

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public Elements getElement() {
        return Elements.Physical;
    }

    @Override
    public boolean IsPercent() {
        return false;
    }

    @Override
    public String locNameForLangFile() {
        return "Physical Damage";
    }
}
