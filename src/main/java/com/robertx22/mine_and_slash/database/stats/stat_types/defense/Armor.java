package com.robertx22.mine_and_slash.database.stats.stat_types.defense;

import com.robertx22.mine_and_slash.database.stats.IUsableStat;
import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.stat_effects.defense.ArmorEffect;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffects;

import java.util.Arrays;
import java.util.List;

public class Armor extends Stat implements IStatEffects, IUsableStat {

    public static Stat INSTANCE = new Armor();

    @Override
    public StatGroup statGroup() {
        return StatGroup.Defenses;
    }

    @Override
    public String getIcon() {
        return "\u25BC";
    }

    @Override
    public String locDescForLangFile() {
        return "Decreases damage taken by a percent";
    }

    public static String GUID = "Armor";

    private Armor() {
        this.minimumValue = 0;
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
        return Elements.Physical;
    }

    @Override
    public boolean IsPercent() {
        return false;
    }

    @Override
    public float MaximumPercent() {
        return 0.75F;
    }

    @Override
    public float AverageStat() {
        return 9;
    }

    @Override
    public List<IStatEffect> GetEffects() {
        return Arrays.asList(new ArmorEffect());
    }

    @Override
    public String locNameForLangFile() {
        return "Armor";
    }
}
