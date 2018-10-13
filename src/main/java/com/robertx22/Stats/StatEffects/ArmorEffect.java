package com.robertx22.stats.StatEffects;

import com.robertx22.database.stats.types.Armor;
import com.robertx22.effectdatas.EffectData;
import com.robertx22.effectdatas.interfaces.IArmorReducable;
import com.robertx22.effectdatas.interfaces.IPenetrable;
import com.robertx22.saveclasses.Unit;
import com.robertx22.stats.IStatEffect;
import com.robertx22.stats.Stat;

public class ArmorEffect implements IStatEffect {

	@Override
	public int GetPriority() {
		return 10;
	}

	@Override
	public EffectData TryModifyEffect(EffectData Effect) {

		if (Effect instanceof IArmorReducable) {

			int pene = 0;

			if (Effect instanceof IPenetrable) {
				IPenetrable ipen = (IPenetrable) Effect;
				pene = ipen.GetArmorPenetration();
			}

			Unit target = Effect.GetTarget();

			Stat armor = target.Stats.get(Armor.class);

			int EffectiveArmor = armor.GetUsableValue(target.level, armor.GetActualVal()) - pene;

			if (EffectiveArmor < 0) {
				EffectiveArmor = 0;
			}

			Effect.Number -= EffectiveArmor * Effect.Number;

		}

		return Effect;
	}

}
