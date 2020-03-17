package com.robertx22.mine_and_slash.database.stats.effects.defense;

import com.robertx22.mine_and_slash.database.stats.IUsableStat;
import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.base.BaseDamageEffect;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.IElementalEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.IElementalPenetrable;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.IElementalResistable;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

public class ElementalResistEffect extends BaseDamageEffect {

    @Override
    public int GetPriority() {
        return Priority.Fifth.priority;
    }

    @Override
    public EffectSides Side() {
        return EffectSides.Target;
    }

    @Override
    public DamageEffect activate(DamageEffect effect, StatData data, Stat stat) {

        int pene = 0;

        if (effect instanceof IElementalPenetrable) {
            IElementalPenetrable ipen = (IElementalPenetrable) effect;
            pene = ipen.GetElementalPenetration();
        }

        IUsableStat resist = (IUsableStat) stat;

        float EffectiveArmor = resist.GetUsableValue(effect.targetData.getLevel(), (int) (data.val - pene));

        effect.number -= EffectiveArmor * effect.number;

        return effect;

    }

    @Override
    public boolean canActivate(DamageEffect effect, StatData data, Stat stat) {
        if (effect instanceof IElementalResistable) {

            IElementalEffect ele = (IElementalEffect) effect;

            if (ele.GetElement() != Elements.Physical) {
                if (ele.GetElement()
                    .equals(stat.getElement())) {
                    return true;
                }
            }

        }
        return false;
    }

}
