package com.robertx22.mine_and_slash.database.stats.types.resources;

import net.minecraft.util.text.TextFormatting;

public class EnergyRegen extends BaseRegenClass {
    public static String GUID = "energy_regen";

    public static EnergyRegen getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public TextFormatting getIconFormat() {
        return TextFormatting.GREEN;
    }

    @Override
    public String getIcon() {
        return "\u0E51";
    }

    @Override
    public String getIconPath() {
        return "regen/energy_regen";
    }

    @Override
    public StatGroup statGroup() {
        return StatGroup.Main;
    }

    private EnergyRegen() {

    }

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public String locNameForLangFile() {
        return "Energy Regen";
    }

    private static class SingletonHolder {
        private static final EnergyRegen INSTANCE = new EnergyRegen();
    }
}
