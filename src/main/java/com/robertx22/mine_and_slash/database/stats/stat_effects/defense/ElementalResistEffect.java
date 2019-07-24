package com.robertx22.mine_and_slash.database.stats.stat_effects.defense;

import com.robertx22.mine_and_slash.database.stats.IUsableStat;
import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.saveclasses.Unit;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.IElementalEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.IElementalPenetrable;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.IElementalResistable;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;

public class ElementalResistEffect implements IStatEffect {

    @Override
    public int GetPriority() {
        return Priority.Fifth.priority;
    }

    @Override
    public EffectSides Side() {
        return EffectSides.Target;
    }

    @Override
    public EffectData TryModifyEffect(EffectData Effect, Unit source, StatData data,
                                      Stat stat) {

        try {
            if (Effect instanceof IElementalResistable) {

                IElementalEffect ele = (IElementalEffect) Effect;

                if (ele.GetElement().equals(stat.Element()) || stat.Element()
                        .equals(Elements.Elemental)) {

                    int pene = 0;

                    if (Effect instanceof IElementalPenetrable) {
                        IElementalPenetrable ipen = (IElementalPenetrable) Effect;
                        pene = ipen.GetElementalPenetration();
                    }

                    IUsableStat resist = (IUsableStat) stat;

                    float EffectiveArmor = resist.GetUsableValue(Effect.targetData.getLevel(), (int) (data.Value - pene));

                    if (EffectiveArmor < 0) {
                        EffectiveArmor = 0;
                    }

                    Effect.number -= EffectiveArmor * Effect.number;

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return Effect;
    }

}
