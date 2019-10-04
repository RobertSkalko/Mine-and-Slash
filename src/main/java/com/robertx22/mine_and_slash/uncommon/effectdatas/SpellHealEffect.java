package com.robertx22.mine_and_slash.uncommon.effectdatas;

import com.robertx22.mine_and_slash.database.spells.bases.BaseSpell;
import com.robertx22.mine_and_slash.saveclasses.ResourcesData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.IHasSpellEffect;

public class SpellHealEffect extends HealEffect implements IHasSpellEffect {

    public BaseSpell spell;

    public SpellHealEffect(ResourcesData.Context data) {

        super(data);

        this.spell = data.spell;
    }

    @Override
    public BaseSpell getSpell() {
        return this.spell;
    }
}
