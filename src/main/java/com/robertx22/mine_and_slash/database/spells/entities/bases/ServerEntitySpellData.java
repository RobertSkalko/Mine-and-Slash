package com.robertx22.mine_and_slash.database.spells.entities.bases;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpellEffect;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.DamageData;

public class ServerEntitySpellData {

    public BaseSpellEffect effect;

    public DamageData data;

    public BaseSpell spell;

    public ServerEntitySpellData(BaseSpellEffect effect, DamageData data,
                                 BaseSpell spell) {
        this.effect = effect;
        this.data = data;
        this.spell = spell;
    }
}
