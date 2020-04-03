package com.robertx22.mine_and_slash.database.stats.types.resources;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.defense.MagicShieldEffect;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffects;
import net.minecraft.util.text.TextFormatting;

public class MagicShield extends Stat implements IStatEffects {
    public static String GUID = "magic_shield";

    private MagicShield() {
        this.minimumValue = 0;
    }

    public static MagicShield getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public String getIconPath() {
        return "resource/magic_shield";
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
    public Elements getElement() {
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

    @Override
    public IStatEffect getEffect() {
        return MagicShieldEffect.INSTANCE;
    }

    private static class SingletonHolder {
        private static final MagicShield INSTANCE = new MagicShield();
    }
}
