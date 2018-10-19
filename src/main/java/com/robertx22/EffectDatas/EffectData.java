package com.robertx22.effectdatas;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.robertx22.saveclasses.Unit;
import com.robertx22.stats.IStatEffect;
import com.robertx22.stats.IStatEffects;
import com.robertx22.stats.Stat;
import com.robertx22.uncommon.datasaving.UnitSaving;

import net.minecraft.entity.EntityLivingBase;

public abstract class EffectData {

	public EffectData(EntityLivingBase source, EntityLivingBase target) {

		this.Source = source;
		this.Target = target;

		try {
			Unit sourceunit = UnitSaving.Load(source);
			if (sourceunit != null) {
				sourceunit.RecalculateStats(source);
				UnitSaving.Save(source, sourceunit);
				sourceUnit = sourceunit;
			}

			Unit targetunit = UnitSaving.Load(target);
			if (targetunit != null) {
				targetunit.RecalculateStats(target);
				UnitSaving.Save(target, targetunit);
				targetUnit = targetunit;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public Unit sourceUnit;
	public Unit targetUnit;

	public EntityLivingBase Source;
	public EntityLivingBase Target;

	public int Number = 0;

	public Unit GetSource() {

		return sourceUnit;
	}

	public Unit GetTarget() {
		return targetUnit;
	}

	public void Activate() {

		if (Source == null || Target == null)
			return;

		TryApplyEffects(this.GetSource());
		TryApplyEffects(this.GetTarget());

		activate();
	}

	protected abstract void activate();

	private EffectData TryApplyEffects(Unit unit) {
		EffectData Data = this;

		List<EffectUnitStat> Effects = new ArrayList<EffectUnitStat>();

		Effects = AddEffects(Effects, unit);

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