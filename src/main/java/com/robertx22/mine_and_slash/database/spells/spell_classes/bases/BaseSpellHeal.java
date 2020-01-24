package com.robertx22.mine_and_slash.database.spells.spell_classes.bases;

import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

public abstract class BaseSpellHeal extends BaseSpell {

    @Override
    public int useTimeTicks() {
        return 10;
    }

    @Override
    public SpellType getSpellType() {
        return SpellType.Self_Heal;
    }

    @Override
    public Elements getElement() {
        return Elements.Physical;
    }

    public int Weight() {
        return super.Weight() * 3;
    }
}


