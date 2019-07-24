package com.robertx22.mine_and_slash.database.stats.stat_effects.offense;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.saveclasses.Unit;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;

public class ElementalFocusEffect implements IStatEffect {

    @Override
    public int GetPriority() {
        return Priority.Second.priority;
    }

    @Override
    public EffectSides Side() {
        return EffectSides.Source;
    }

    @Override
    public EffectData TryModifyEffect(EffectData Effect, Unit source, StatData data,
                                      Stat stat) {

        try {
            if (Effect instanceof DamageEffect) {

                DamageEffect dmgeffect = (DamageEffect) Effect;

                if (dmgeffect.element != null && dmgeffect.element != Elements.Physical) {

                    float amount = dmgeffect.number * data.Value / 100;

                    if (dmgeffect.element.equals(stat.Element())) {
                        dmgeffect.number += amount;
                    } else {
                        dmgeffect.number -= amount;
                    }

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return Effect;
    }

}
