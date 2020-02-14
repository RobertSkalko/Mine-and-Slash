package com.robertx22.mine_and_slash.database.stats.types.resources;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.saveclasses.Unit;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.text.TextFormatting;

public class Health extends Stat {
    public static String GUID = "health";

    private Health() {
        this.minimumValue = 1;
    }

    public static Health getInstance() {
        return SingletonHolder.INSTANCE;
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
    public Elements getElement() {
        return null;
    }

    @Override
    public boolean IsPercent() {
        return false;
    }

    public int CurrentValue(LivingEntity entity, Unit unit) {

        float mult = entity.getHealth() / entity.getMaxHealth();

        return (int) (mult * unit.healthData().val);

    }

    @Override
    public String locNameForLangFile() {
        return "Health";
    }

    private static class SingletonHolder {
        private static final Health INSTANCE = new Health();
    }
}
