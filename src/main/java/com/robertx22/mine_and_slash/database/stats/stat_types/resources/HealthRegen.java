package com.robertx22.mine_and_slash.database.stats.stat_types.resources;

import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

public class HealthRegen extends BaseRegenClass {
    public static String GUID = "Health Regen";

    @Override
    public StatGroup statGroup() {
        return StatGroup.Main;
    }

    public HealthRegen() {

    }

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public boolean ScalesToLevel() {
        return true;
    }

    @Override
    public Elements Element() {
        return null;
    }

    @Override
    public boolean IsPercent() {
        return false;
    }

    @Override
    public String locNameForLangFile() {
        return "Health Regen";
    }
}
