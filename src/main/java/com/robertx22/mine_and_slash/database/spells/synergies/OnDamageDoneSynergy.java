package com.robertx22.mine_and_slash.database.spells.synergies;

import com.robertx22.mine_and_slash.uncommon.effectdatas.SpellDamageEffect;

public abstract class OnDamageDoneSynergy extends Synergy {

    public abstract void tryActivate(SpellDamageEffect effect);

}
