package com.robertx22.mine_and_slash.database.stats.effects.defense;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.base.BaseDamageEffect;
import com.robertx22.mine_and_slash.database.stats.types.defense.DodgeRating;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData.EffectTypes;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;

public class DodgeEffect extends BaseDamageEffect {

    @Override
    public int GetPriority() {
        return Priority.AlmostLast.priority;
    }

    @Override
    public EffectSides Side() {
        return EffectSides.Target;
    }

    @Override
    public DamageEffect activate(DamageEffect effect, StatData data, Stat stat) {
        DodgeRating dodge = (DodgeRating) stat;

        float chance = dodge.GetUsableValue(effect.targetData.getLevel(), (int) data.val) * 100;

        if (RandomUtils.roll(chance)) {
            DamageEffect dmgeffect = (DamageEffect) effect;

            effect.number = 0;
            dmgeffect.isDodged = true;

        }
        return effect;
    }

    @Override
    public boolean canActivate(DamageEffect effect, StatData data, Stat stat) {
        return !effect.getEffectType().equals(EffectTypes.SPELL);
    }

}
