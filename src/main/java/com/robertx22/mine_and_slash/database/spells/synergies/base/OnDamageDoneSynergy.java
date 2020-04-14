package com.robertx22.mine_and_slash.database.spells.synergies.base;

import com.robertx22.mine_and_slash.uncommon.effectdatas.SpellDamageEffect;

public abstract class OnDamageDoneSynergy extends Synergy {

    public abstract void tryActivate(SpellDamageEffect effect);

}
