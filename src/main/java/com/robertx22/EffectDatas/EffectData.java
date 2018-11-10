package com.robertx22.effectdatas;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.robertx22.saveclasses.Unit;
import com.robertx22.stats.IStatEffect;
import com.robertx22.stats.IStatEffect.EffectSides;
import com.robertx22.stats.IStatEffects;
import com.robertx22.stats.Stat;
import com.robertx22.uncommon.datasaving.UnitSaving;

import net.minecraft.entity.EntityLivingBase;

public abstract class EffectData {

	public EffectData(EntityLivingBase source, EntityLivingBase target) {

		this.Source = source;
		this.Target = target;

		try {
			targetUnit = UnitSaving.Load(target);
			sourceUnit = UnitSaving.Load(source);

			if (sourceUnit != null && targetUnit != null) {
				sourceUnit.ReloadStatsAndSave(source);
				targetUnit.ReloadStatsAndSave(target);

			} else {
				this.canceled = true;

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public EffectData(EntityLivingBase source, EntityLivingBase target, Unit sourceUnit, Unit targetUnit) {

		this.Source = source;
		this.Target = target;

		if (sourceUnit != null && targetUnit != null) {
			this.sourceUnit = sourceUnit;
			this.targetUnit = targetUnit;

		} else {
			this.canceled = true;
		}

	}

	public EffectTypes Type = EffectTypes.NORMAL;

	public enum EffectTypes {
		NORMAL, SPELL, BASIC_ATTACK, BONUS_ATTACK
	}

	public boolean canceled = false;
	public Unit sourceUnit;
	public Unit targetUnit;

	public EntityLivingBase Source;
	public EntityLivingBase Target;

	public float Number = 0;

	public Unit GetSource() {

		return sourceUnit;
	}

	public Unit GetTarget() {
		return targetUnit;
	}

	public void Activate() {

		if (Source == null || Target == null || canceled == true || sourceUnit == null || targetUnit == null)
			return;

		TryApplyEffects(this.GetSource());
		TryApplyEffects(this.GetTarget());

		if (this.canceled != true) {

			UnitSaving.Save(Source, sourceUnit);
			UnitSaving.Save(Target, targetUnit);

			activate();

		}
	}

	protected abstract void activate();

	private EffectData TryApplyEffects(Unit unit) {

		if (this.canceled) {
			return this;
		}

		EffectData Data = this;

		List<EffectUnitStat> Effects = new ArrayList<EffectUnitStat>();

		Effects = AddEffects(Effects, unit);

		Effects.sort((Comparator<EffectUnitStat>) new EffectUnitStat());

		for (EffectUnitStat item : Effects) {
			if (item.stat.Value > 0) {

				if (AffectsThisUnit(item.effect, Data, item.source)) {
					item.effect.TryModifyEffect(Data, item.source, item.stat);
				}

			}
		}

		return Data;
	}

	public boolean AffectsThisUnit(IStatEffect effect, EffectData data, Unit source) {

		if (effect.Side().equals(EffectSides.Target)) {
			return source.equals(data.targetUnit);

		} else {
			return source.equals(data.sourceUnit);
		}
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