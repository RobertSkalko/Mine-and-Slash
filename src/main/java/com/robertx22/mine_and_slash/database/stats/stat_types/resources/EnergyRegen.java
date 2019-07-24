package com.robertx22.mine_and_slash.database.stats.stat_types.resources;

public class EnergyRegen extends BaseRegenClass {
    public static String GUID = "Energy Regen";

    @Override
    public StatGroup statGroup() {
        return StatGroup.Main;
    }

    public EnergyRegen() {

    }

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public boolean ScalesToLevel() {
        return false;
    }

    @Override
    public String locNameForLangFile() {
        return "Energy Regen";
    }
}
