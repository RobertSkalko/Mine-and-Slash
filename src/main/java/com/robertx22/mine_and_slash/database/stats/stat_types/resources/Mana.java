package com.robertx22.mine_and_slash.database.stats.stat_types.resources;

import com.robertx22.mine_and_slash.database.stats.FillableStat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import net.minecraft.util.text.TextFormatting;

public class Mana extends FillableStat {
    public static String GUID = "Mana";

    @Override
    public TextFormatting getIconFormat() {
        return TextFormatting.AQUA;
    }

    @Override
    public String getIcon() {
        return "\u25CE";
    }

    @Override
    public int iconSpriteNumber() {
        return 2;
    }

    @Override
    public StatGroup statGroup() {
        return StatGroup.Main;
    }

    @Override
    public String locDescForLangFile() {
        return "Mana is used to cast spells";
    }

    public Mana() {

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
    public Elements Element() {
        return null;
    }

    @Override
    public boolean IsPercent() {
        return false;
    }

    @Override
    public String locNameForLangFile() {
        return "Mana";
    }
}
