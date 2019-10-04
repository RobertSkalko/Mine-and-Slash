package com.robertx22.mine_and_slash.database.stats.stat_types.defense;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.stat_effects.defense.DodgeEffect;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffects;

public class Dodge extends Stat implements IStatEffects {

    public static Stat INSTANCE = new Dodge();
    public static String GUID = "Dodge";

    @Override
    public StatGroup statGroup() {
        return StatGroup.Defenses;
    }

    @Override
    public String locDescForLangFile() {
        return "Chance to ignore all damage";
    }

    @Override
    public IStatEffect getEffect() {
        return new DodgeEffect();
    }

    private Dodge() {
        maximumValue = 75;
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
        return "Dodge";
    }
}
