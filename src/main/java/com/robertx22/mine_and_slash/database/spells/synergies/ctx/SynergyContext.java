package com.robertx22.mine_and_slash.database.spells.synergies.ctx;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellCastContext;

public abstract class SynergyContext {

    public SpellCastContext ctx;

    public SynergyContext(SpellCastContext ctx) {
        this.ctx = ctx;
    }
}
