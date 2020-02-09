package com.robertx22.mine_and_slash.database.stats.types.resources;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import net.minecraft.util.text.TextFormatting;

public class HealthRegen extends BaseRegenClass {
    public static Stat INSTANCE = new HealthRegen();
    public static String GUID = "Health Regen";

    @Override
    public TextFormatting getIconFormat() {
        return TextFormatting.RED;
    }

    @Override
    public String getIcon() {
        return "\u0E51";
    }

    @Override
    public String getIconPath() {
        return "regen/hp_regen";
    }

    @Override
    public StatGroup statGroup() {
        return StatGroup.Main;
    }

    private HealthRegen() {
        this.minimumValue = 0.1F;
    }

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public Elements getElement() {
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
