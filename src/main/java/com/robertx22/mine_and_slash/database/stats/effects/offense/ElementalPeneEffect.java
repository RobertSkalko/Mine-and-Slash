package com.robertx22.mine_and_slash.database.stats.effects.offense;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.base.BaseAnyEffect;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.IElementalPenetrable;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.IPenetrable;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

public class ElementalPeneEffect extends BaseAnyEffect {

    @Override
    public int GetPriority() {
        return Priority.First.priority;
    }

    @Override
    public EffectSides Side() {
        return EffectSides.Source;
    }

    @Override
    public EffectData activate(EffectData effect, StatData data, Stat stat) {
        if (stat.getElement() == Elements.Physical) {
            IPenetrable ipene = (IPenetrable) effect;
            ipene.SetArmorPenetration(ipene.GetArmorPenetration() + (int) data.val);

        } else {
            IElementalPenetrable ipene = (IElementalPenetrable) effect;
            ipene.addElementalPenetration((int) data.val);
        }

        return effect;
    }

    @Override
    public boolean canActivate(EffectData effect, StatData data, Stat stat) {
        if (effect instanceof IElementalPenetrable) {
            IElementalPenetrable ele = (IElementalPenetrable) effect;
            return ele.GetElement().equals(stat.getElement()) || stat.getElement().equals(Elements.Elemental);
        }

        return false;
    }

}
