package com.robertx22.mine_and_slash.database.stats.stat_types.resources;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import net.minecraft.util.text.TextFormatting;

public class MagicShield extends Stat {
    public static String GUID = "magic_shield";

    public static MagicShield INSTANCE = new MagicShield();

    private MagicShield() {

    }

    @Override
    public TextFormatting getIconFormat() {
        return TextFormatting.BLUE;
    }

    @Override
    public String getIcon() {
        return "\u2764";
    }

    @Override
    public StatGroup statGroup() {
        return StatGroup.Main;
    }

    @Override
    public String locDescForLangFile() {
        return "Works like extra health. But regen is separate.";
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
        return "Magic Shield";
    }
}
