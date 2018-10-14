package com.robertx22.effectdatas;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.robertx22.saveclasses.Unit;
import com.robertx22.saving.Saving;
import com.robertx22.stats.IStatEffect;
import com.robertx22.stats.IStatEffects;
import com.robertx22.stats.Stat;

import net.minecraft.entity.EntityLivingBase;

public abstract class EffectData {
	public EntityLivingBase Source;
	public EntityLivingBase Target;

	public int Number;

	public Unit GetSource() {

		return Saving.Load(Source, Unit.class);
	}

	public Unit GetTarget() {
		return Saving.Load(Target, Unit.class);
	}

	public abstract void Activate();

	public EffectData TryApplyEffects() throws Exception {
		EffectData Data = this;

		List<EffectUnitStat> Effects = new ArrayList<EffectUnitStat>();

		Effects = AddEffects(Effects, GetTarget());
		Effects = AddEffects(Effects, GetSource());

		Effects.sort((Comparator<EffectUnitStat>) new EffectUnitStat());

		for (EffectUnitStat item : Effects) {
			item.effect.TryModifyEffect(Data, item.source, item.stat);
		}

		return Data;
	}

	private List<EffectUnitStat> AddEffects(List<EffectUnitStat> effects, Unit unit) {
		if (unit != null) {
			for (Stat stat : unit.Stats.values()) {
				if (stat instanceof IStatEffects) {
					for (IStatEffect effect : ((IStatEffects) stat).GetEffects()) {
						effects.add(new EffectUnitStat(effect, unit, stat));
					}

				}

			}
		}
		return effects;
	}

}