package com.robertx22.stats.StatEffects.offense;

import com.robertx22.effectdatas.DamageEffect;
import com.robertx22.effectdatas.EffectData;
import com.robertx22.saveclasses.StatData;
import com.robertx22.saveclasses.Unit;
import com.robertx22.stats.IStatEffect;
import com.robertx22.stats.Stat;
import com.robertx22.stats.WeaponDamageStat;

public class WeaponDamageEffect implements IStatEffect {

    @Override
    public int GetPriority() {
	return 0;
    }

    @Override
    public EffectSides Side() {
	return EffectSides.Source;
    }

    @Override
    public EffectData TryModifyEffect(EffectData Effect, Unit source, StatData data, Stat stat) {

	try {
	    if (Effect instanceof DamageEffect && stat instanceof WeaponDamageStat) {

		WeaponDamageStat weapon = (WeaponDamageStat) stat;

		if (weapon.weaponType().equals(Effect.weaponType)) {
		    DamageEffect dmgeffect = (DamageEffect) Effect;
		    dmgeffect.Number *= 1 + data.Value / 100;

		}
	    }

	} catch (Exception e) {
	    e.printStackTrace();
	}

	return Effect;
    }

}