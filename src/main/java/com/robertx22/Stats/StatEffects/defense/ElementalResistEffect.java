package com.robertx22.stats.StatEffects.defense;

import com.robertx22.effectdatas.EffectData;
import com.robertx22.effectdatas.interfaces.IElementalEffect;
import com.robertx22.effectdatas.interfaces.IElementalPenetrable;
import com.robertx22.effectdatas.interfaces.IElementalResistable;
import com.robertx22.saveclasses.StatData;
import com.robertx22.saveclasses.Unit;
import com.robertx22.stats.IStatEffect;
import com.robertx22.stats.Stat;
import com.robertx22.stats.UsableStat;

public class ElementalResistEffect implements IStatEffect {

    @Override
    public int GetPriority() {
	return 20;
    }

    @Override
    public EffectSides Side() {
	return EffectSides.Target;
    }

    @Override
    public EffectData TryModifyEffect(EffectData Effect, Unit source, StatData data, Stat stat) {

	try {
	    if (Effect instanceof IElementalResistable) {

		IElementalEffect ele = (IElementalEffect) Effect;

		if (ele.GetElement().equals(stat.Element())) {
		    int pene = 0;

		    if (Effect instanceof IElementalPenetrable) {
			IElementalPenetrable ipen = (IElementalPenetrable) Effect;
			pene = ipen.GetElementalPenetration();
		    }

		    UsableStat resist = (UsableStat) stat;

		    float EffectiveArmor = resist.GetUsableValue(Effect.targetData.getLevel(),
			    (int) (data.Value - pene));

		    if (EffectiveArmor < 0) {
			EffectiveArmor = 0;
		    }

		    Effect.Number -= EffectiveArmor * Effect.Number;

		}
	    }

	} catch (Exception e) {
	    e.printStackTrace();
	}

	return Effect;
    }

}
