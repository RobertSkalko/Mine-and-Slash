package com.robertx22.mine_and_slash.database.stats.effects.offense;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.base.BaseDamageEffect;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

public class PhysicalToHighestEle extends BaseDamageEffect {

    public static final PhysicalToHighestEle INSTANCE = new PhysicalToHighestEle();

    @Override
    public int GetPriority() {
        return Priority.Third.priority;
    }

    @Override
    public EffectSides Side() {
        return EffectSides.Source;
    }

    @Override
    public DamageEffect activate(DamageEffect effect, StatData data, Stat stat) {
        float val = effect.number;

        Elements ele = effect.getHighestBonusElementalDamageElement();

        if (ele != null) {
            float multi = data.getMultiplier();
            float given = (val * multi) - val;

            effect.addBonusEleDmg(ele, given);
            effect.number -= given;
        }

        return effect;
    }

    @Override
    public boolean canActivate(DamageEffect effect, StatData data, Stat stat) {
        return effect.element == Elements.Physical;
    }

}

