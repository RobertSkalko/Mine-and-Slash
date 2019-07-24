package com.robertx22.mine_and_slash.uncommon.effectdatas;

import com.robertx22.mine_and_slash.spells.bases.BaseSpell;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.IHasSpellEffect;

public class SpellHealEffect extends HealEffect implements IHasSpellEffect {

    public BaseSpell spell;

    public SpellHealEffect(HealData data) {

        super(data);

        this.spell = data.spell;
    }

    @Override
    public BaseSpell getSpell() {
        return this.spell;
    }
}
