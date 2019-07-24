package com.robertx22.mine_and_slash.uncommon.effectdatas;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.db_lists.initializers.Stats;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.saveclasses.Unit;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect.EffectSides;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffects;
import net.minecraft.entity.LivingEntity;

import java.util.ArrayList;
import java.util.List;

public abstract class EffectData {

    public UnitData sourceData;
    public UnitData targetData;

    public boolean canceled = false;
    public Unit sourceUnit;
    public Unit targetUnit;

    public LivingEntity source;
    public LivingEntity target;

    public float number = 0;

    public EffectData(LivingEntity source, LivingEntity target) {

        this.source = source;
        this.target = target;

        if (target != null) {
            this.targetData = Load.Unit(target);
        }
        if (source != null) {
            this.sourceData = Load.Unit(source);

        }
        if (source != null) {

            try {
                if (target != null) {
                    targetUnit = targetData.getUnit();
                }

                sourceUnit = sourceData.getUnit();

                if (sourceUnit != null) {
                    sourceData.recalculateStats(source);

                } else {
                    this.canceled = true;
                }
                if (targetUnit != null) {

                    targetData.recalculateStats(target);

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public EffectData(LivingEntity source, LivingEntity target, UnitData sourceData,
                      UnitData targetData) {

        this.source = source;
        this.target = target;

        if (sourceData != null && targetData != null) {
            this.sourceData = sourceData;
            this.targetData = targetData;

            this.sourceUnit = sourceData.getUnit();
            this.targetUnit = targetData.getUnit();

        } else {
            this.canceled = true;
        }

    }

    private EffectTypes effectType = EffectTypes.BASIC_ATTACK;

    public EffectTypes getEffectType() {
        return effectType;
    }

    public void setEffectType(EffectTypes effectType, WeaponTypes weaponType) {
        this.effectType = effectType;
        this.weaponType = weaponType;
    }

    public WeaponTypes weaponType = WeaponTypes.None;

    public enum EffectTypes {
        NORMAL,
        SPELL,
        BASIC_ATTACK,
        BONUS_ATTACK,
        REFLECT,
    }

    public Unit GetSource() {

        return sourceUnit;
    }

    public Unit GetTarget() {
        return targetUnit;
    }

    public void Activate() {

        if (source == null || target == null || canceled == true || sourceUnit == null || targetUnit == null || sourceData == null || targetData == null)
            return;

        TryApplyEffects(this.GetSource());
        TryApplyEffects(this.GetTarget());

        if (this.canceled != true) {

            activate();

        }

    }

    protected abstract void activate();

    protected EffectData TryApplyEffects(Unit unit) {

        if (this.canceled) {
            return this;
        }

        List<EffectUnitStat> Effects = new ArrayList<EffectUnitStat>();

        Effects = AddEffects(Effects, unit);

        Effects.sort(new EffectUnitStat());

        for (EffectUnitStat item : Effects) {
            if (item.stat.Value != 0) {
                if (AffectsThisUnit(item.effect, this, item.source)) {
                    item.effect.TryModifyEffect(this, item.source, item.stat, item.stat.GetStat());
                }

            }
        }

        return this;
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

            for (IStatEffects stateffects : Stats.allPreGenMapStatLists.get(IStatEffects.class)) {
                StatData stat = unit.getStat((Stat) stateffects);
                if (stat.Value != 0) {
                    for (IStatEffect stateffect : stateffects.GetEffects()) {
                        effects.add(new EffectUnitStat(stateffect, unit, stat));
                    }
                }

            }

        }

        return effects;
    }

}