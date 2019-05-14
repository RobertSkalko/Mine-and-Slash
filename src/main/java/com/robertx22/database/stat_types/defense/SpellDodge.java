package com.robertx22.database.stat_types.defense;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stats.IStatEffect;
import com.robertx22.database.stats.IStatEffects;
import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_effects.defense.SpellDodgeEffect;
import com.robertx22.uncommon.enumclasses.Elements;

public class SpellDodge extends Stat implements IStatEffects {
    public static String GUID = "Spell Dodge";

    @Override
    public String unlocString() {
	return "spell_dodge";
    }

    @Override
    public List<IStatEffect> GetEffects() {
	return Arrays.asList(new SpellDodgeEffect());
    }

    public SpellDodge() {
    }

    @Override
    public String Guid() {
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

}
