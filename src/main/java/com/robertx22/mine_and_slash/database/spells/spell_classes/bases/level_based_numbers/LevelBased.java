package com.robertx22.mine_and_slash.database.spells.spell_classes.bases.level_based_numbers;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellCastContext;

public class LevelBased {

    public final float levelOne;
    public final float maxLevel;

    public LevelBased(float levelOne, float maxLevel) {
        this.levelOne = levelOne;
        this.maxLevel = maxLevel;
    }

    public float getValueFor(int spellLevel, BaseSpell spell) {

        if (levelOne == maxLevel) {
            return maxLevel;
        }

        float multi = spellLevel / spell.getMaxSpellLevelBuffed();
        return levelOne + ((maxLevel - levelOne) * multi);
    }

    public float getValueFor(SpellCastContext ctx) {
        return getValueFor(ctx.data.getSpellLevel(ctx.spell), ctx.spell);
    }
}
