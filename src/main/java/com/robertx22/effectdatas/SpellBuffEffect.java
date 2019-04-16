package com.robertx22.effectdatas;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.robertx22.effectdatas.interfaces.IBuffableSpell;
import com.robertx22.saveclasses.StatData;
import com.robertx22.saveclasses.Unit;
import com.robertx22.spells.bases.BaseSpell.SpellType;
import com.robertx22.stats.IStatEffect;
import com.robertx22.stats.IStatEffect.EffectSides;
import com.robertx22.stats.IStatEffects;
import com.robertx22.uncommon.capability.WorldData.IWorldData;
import com.robertx22.uncommon.datasaving.Load;

import net.minecraft.entity.EntityLivingBase;

public class SpellBuffEffect extends EffectData implements IBuffableSpell {

    public IBuffableSpell buffable;

    private SpellBuffType buff = SpellBuffType.None;

    public SpellBuffEffect(EntityLivingBase source, IBuffableSpell buffable) {

	super(source, null);
	this.canceled = false;
	// a bit funky, didn't expect effects with just 1 entity

	this.buffable = buffable;

	this.Source = source;

	this.sourceData = Load.Unit(source);

	IWorldData world = Load.World(source.world);

	try {
	    sourceUnit = sourceData.getUnit();

	    if (sourceUnit != null) {
		sourceData.recalculateStats(source, world);

	    } else {
		this.canceled = true;

	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}

    }

    protected void activate() {
	buffable.setBuff(buff);
    }

    public void Activate() {

	if (Source == null || canceled == true || sourceUnit == null || sourceData == null)
	    return;

	TryApplyEffects(this.GetSource());

	if (this.canceled != true) {

	    sourceData.setUnit(sourceUnit, Source);

	    activate();

	}
    }

    // start

    protected EffectData TryApplyEffects(Unit unit) {

	if (this.canceled) {
	    return this;
	}

	EffectData Data = this;

	List<EffectUnitStat> Effects = new ArrayList<EffectUnitStat>();

	Effects = AddEffects(Effects, unit);

	Effects.sort((Comparator<EffectUnitStat>) new EffectUnitStat());

	for (EffectUnitStat item : Effects) {
	    if (item.stat.Value != 0) {
		if (AffectsThisUnit(item.effect, Data, item.source)) {
		    item.effect.TryModifyEffect(Data, item.source, item.stat, item.stat.GetStat());
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
	    for (StatData stat : unit.MyStats.values()) {
		if (stat.GetStat() instanceof IStatEffects) {
		    for (IStatEffect effect : ((IStatEffects) stat.GetStat()).GetEffects()) {
			effects.add(new EffectUnitStat(effect, unit, stat));
		    }

		}

	    }
	}
	return effects;
    }

    @Override
    public void setBuff(SpellBuffType buff) {
	this.buff = buff;

    }

    @Override
    public SpellBuffType getBuff() {
	return buff;
    }

    @Override
    public void setType(SpellType type) {
	this.buffable.setType(type);

    }

    @Override
    public SpellType getType() {
	return this.buffable.getType();
    }

}
