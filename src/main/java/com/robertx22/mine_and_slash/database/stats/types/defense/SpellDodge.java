package com.robertx22.mine_and_slash.database.stats.types.defense;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.defense.SpellDodgeEffect;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffects;

public class SpellDodge extends Stat implements IStatEffects {
    public static String GUID = "Spell Dodge";

    @Override
    public String locDescForLangFile() {
        return "Chance to Ignore spell damage";
    }

    @Override
    public IStatEffect getEffect() {
        return new SpellDodgeEffect();
    }

    public SpellDodge() {
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
        return true;
    }

    @Override
    public String locNameForLangFile() {
        return "Spell Dodge";
    }
}
