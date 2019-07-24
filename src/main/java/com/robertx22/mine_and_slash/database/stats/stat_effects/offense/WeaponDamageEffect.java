package com.robertx22.mine_and_slash.database.stats.stat_effects.offense;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.stat_types.generated.WeaponDamage;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.saveclasses.Unit;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;

public class WeaponDamageEffect implements IStatEffect {

    @Override
    public int GetPriority() {
        return Priority.First.priority;
    }

    @Override
    public EffectSides Side() {
        return EffectSides.Source;
    }

    @Override
    public EffectData TryModifyEffect(EffectData Effect, Unit source, StatData data,
                                      Stat stat) {

        try {
            if (Effect instanceof DamageEffect && stat instanceof WeaponDamage) {

                WeaponDamage weapon = (WeaponDamage) stat;

                if (weapon.weaponType().equals(Effect.weaponType)) {
                    DamageEffect dmgeffect = (DamageEffect) Effect;
                    dmgeffect.number *= data.getMultiplier();

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return Effect;
    }

}