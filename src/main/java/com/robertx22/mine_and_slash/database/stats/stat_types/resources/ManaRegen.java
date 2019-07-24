package com.robertx22.mine_and_slash.database.stats.stat_types.resources;

public class ManaRegen extends BaseRegenClass {
    public static String GUID = "Mana Regen";

    @Override
    public StatGroup statGroup() {
        return StatGroup.Main;
    }

    public ManaRegen() {

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
        return "Mana Regen";
    }
}
