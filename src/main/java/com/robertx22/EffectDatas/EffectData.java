package com.robertx22.effectdatas;

import java.util.ArrayList;
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

	public EffectData TryApplyEffects() {
		EffectData Data = this;

		List<EffectUnitStat> Effects = new ArrayList<EffectUnitStat>();

		Effects = AddEffects(Effects, GetTarget());
		Effects = AddEffects(Effects, GetSource());

		List<EffectUnitStat> sorted = SortList(Effects);

		for (EffectUnitStat item : sorted) {

			for (IStatEffect stateffect : item.effects.GetEffects()) {
				stateffect.TryModifyEffect(Data, item.source, item.stat);
			}

		}

		return Data;
	}

	private List<EffectUnitStat> AddEffects(List<EffectUnitStat> effects, Unit unit) {
		if (unit != null) {
			for (Stat stat : unit.Stats.values()) {
				if (stat instanceof IStatEffects) {
					effects.add(new EffectUnitStat((IStatEffects) stat, unit, stat));
				}

			}
		}
		return effects;
	}

	private static List<EffectUnitStat> SortList(List<EffectUnitStat> effects) {
		List<EffectUnitStat> sorted = new ArrayList<EffectUnitStat>();

		while (effects.size() != sorted.size()) {
			for (int i = 0; i < 100; i++) {
				for (int x = 0; x < effects.size(); x++) {
					for (IStatEffect effect : effects.get(x).effects.GetEffects()) {
						if ((int) effect.GetPriority() == i) {
							sorted.add(effects.get(x));
						}
					}
				}
			}
		}
		return sorted;
	}

}