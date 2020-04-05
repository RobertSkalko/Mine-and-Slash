package com.robertx22.mine_and_slash.database.spells.spell_classes.bases.level_based_numbers;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellCastContext;
import com.robertx22.mine_and_slash.saveclasses.spells.IAbility;

public class LevelBased {

    public final float levelOne;
    public final float maxLevel;

    public LevelBased(float levelOne, float maxLevel) {
        this.levelOne = levelOne;
        this.maxLevel = maxLevel;
    }

    public float getValueFor(int abilityLevel, IAbility ability) {

        if (levelOne == maxLevel) {
            return maxLevel;
        }

        float multi = abilityLevel / ability.getMaxSpellLevelBuffed();
        return levelOne + ((maxLevel - levelOne) * multi);
    }

    public float getValueFor(SpellCastContext ctx) {
        return getValueFor(ctx.spellsCap.getLevelOf(ctx.ability), ctx.ability);
    }
}
