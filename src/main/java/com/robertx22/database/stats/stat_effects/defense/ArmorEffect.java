package com.robertx22.database.stats.stat_effects.defense;

import com.robertx22.database.stats.IStatEffect;
import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.UsableStat;
import com.robertx22.saveclasses.StatData;
import com.robertx22.saveclasses.Unit;
import com.robertx22.uncommon.effectdatas.EffectData;
import com.robertx22.uncommon.effectdatas.interfaces.IArmorReducable;
import com.robertx22.uncommon.effectdatas.interfaces.IPenetrable;

public class ArmorEffect implements IStatEffect {

    @Override
    public int GetPriority() {
	return 10;
    }

    @Override
    public EffectSides Side() {
	return EffectSides.Target;
    }

    @Override
    public EffectData TryModifyEffect(EffectData Effect, Unit source, StatData data, Stat stat) {

	try {
	    if (Effect instanceof IArmorReducable) {

		int pene = 0;

		if (Effect instanceof IPenetrable) {
		    IPenetrable ipen = (IPenetrable) Effect;
		    pene = ipen.GetArmorPenetration();
		}

		UsableStat armor = (UsableStat) stat;

		float EffectiveArmor = armor.GetUsableValue(Effect.targetData.getLevel(), (int) (data.Value - pene));

		if (EffectiveArmor < 0) {
		    EffectiveArmor = 0;
		}

		float old = Effect.Number;

		Effect.Number -= EffectiveArmor * Effect.Number;

	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}

	return Effect;
    }

}
