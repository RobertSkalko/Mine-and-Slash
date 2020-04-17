package com.robertx22.mine_and_slash.database.spells.synergies.base;

import com.robertx22.mine_and_slash.uncommon.effectdatas.SpellHealEffect;

public abstract class OnHealedSynergy extends Synergy {

    public abstract void tryActivate(SpellHealEffect effect);

}
