package com.robertx22.mine_and_slash.database.stats.stat_types.defense;

import com.robertx22.mine_and_slash.database.stats.IUsableStat;
import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.stat_effects.defense.DodgeEffect;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffects;

public class DodgeRating extends Stat implements IStatEffects, IUsableStat {

    public static Stat INSTANCE = new DodgeRating();
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
    public String getIconPath() {
        return "dodge";
    }

    @Override
    public IStatEffect getEffect() {
        return new DodgeEffect();
    }

    private DodgeRating() {

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
        return "Dodge Rating";
    }

    @Override
    public float MaximumPercent() {
        return 0.9F;
    }

    @Override
    public float AverageStat() {
        return 8;
    }
}
