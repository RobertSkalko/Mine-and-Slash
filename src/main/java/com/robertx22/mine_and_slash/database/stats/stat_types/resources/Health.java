package com.robertx22.mine_and_slash.database.stats.stat_types.resources;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.saveclasses.Unit;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.text.TextFormatting;

public class Health extends Stat {
    public static String GUID = "Health";

    public static Health INSTANCE = new Health();

    private Health() {

    }

    @Override
    public TextFormatting getIconFormat() {
        return TextFormatting.RED;
    }

    @Override
    public String getIcon() {
        return "\u2764";
    }

    @Override
    public String getIconPath() {
        return "resource/hp";
    }

    @Override
    public StatGroup statGroup() {
        return StatGroup.Main;
    }

    @Override
    public String locDescForLangFile() {
        return "Allows you to take more damage from mobs";
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

    public int CurrentValue(LivingEntity entity, Unit unit) {

        float mult = entity.getHealth() / entity.getMaxHealth();

        return (int) (mult * unit.healthData().Value);

    }

    @Override
    public String locNameForLangFile() {
        return "Health";
    }
}
