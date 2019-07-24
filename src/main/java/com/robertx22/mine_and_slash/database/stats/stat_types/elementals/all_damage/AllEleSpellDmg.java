package com.robertx22.mine_and_slash.database.stats.stat_types.elementals.all_damage;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.stat_effects.offense.AllEleSpellDmgEffect;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffects;

import java.util.Arrays;
import java.util.List;

public class AllEleSpellDmg extends Stat implements IStatEffects {

    public static String GUID = "AllEleSpellDmg";

    @Override
    public String locDescForLangFile() {
        return "Increases All Elemental Spell DMG";
    }

    public AllEleSpellDmg() {

    }

    @Override
    public boolean ScalesToLevel() {
        return false;
    }

    @Override
    public boolean IsPercent() {
        return true;
    }

    @Override
    public List<IStatEffect> GetEffects() {
        return Arrays.asList(new AllEleSpellDmgEffect());
    }

    @Override
    public String locNameForLangFile() {
        return "All Ele Spell Damage";
    }

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public Elements Element() {
        return Elements.Physical;
    }
}
