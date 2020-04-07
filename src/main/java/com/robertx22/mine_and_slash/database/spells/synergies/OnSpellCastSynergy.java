package com.robertx22.mine_and_slash.database.spells.synergies;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellCastContext;

public abstract class OnSpellCastSynergy extends Synergy {

    public abstract void tryActivate(SpellCastContext ctx);

}

