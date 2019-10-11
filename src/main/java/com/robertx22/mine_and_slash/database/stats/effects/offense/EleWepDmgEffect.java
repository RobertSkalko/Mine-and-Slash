package com.robertx22.mine_and_slash.database.stats.effects.offense;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.types.generated.EleWepDmg;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.saveclasses.Unit;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;

public class EleWepDmgEffect implements IStatEffect {

    public static EleWepDmgEffect INSTANCE = new EleWepDmgEffect();

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
            if (Effect instanceof DamageEffect && stat instanceof EleWepDmg) {
                EleWepDmg wepStat = (EleWepDmg) stat;

                if (wepStat.weaponType().equals(Effect.weaponType)) {
                    DamageEffect dmg = (DamageEffect) Effect;

                    if (dmg.isElemental()) {
                        dmg.number *= data.getMultiplier();
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return Effect;
    }

}