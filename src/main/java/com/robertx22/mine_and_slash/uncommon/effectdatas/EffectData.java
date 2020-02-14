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
                    sourceData.tryRecalculateStats(source);

                } else {
                    this.canceled = true;
                }
                if (targetUnit != null) {

                    targetData.tryRecalculateStats(target);

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public EffectData(LivingEntity source, LivingEntity target, UnitData sourceData, UnitData targetData) {

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
        DOT_DMG,
    }

    public Unit GetSource() {

        return sourceUnit;
    }

    public Unit GetTarget() {
        return targetUnit;
    }

    public void Activate() {

        calculateEffects();

        if (this.canceled != true) {
            activate();
        }

    }

    boolean effectsCalculated = false;

    public void calculateEffects() {
        if (!effectsCalculated) {
            effectsCalculated = true;
            if (source == null || target == null || canceled == true || sourceUnit == null || targetUnit == null || sourceData == null || targetData == null)
                return;

            TryApplyEffects(this.GetSource(), EffectSides.Source);
            TryApplyEffects(this.GetTarget(), EffectSides.Target);

        }

    }

    protected abstract void activate();

    protected EffectData TryApplyEffects(Unit unit, EffectSides side) {

        if (this.canceled) {
            return this;
        }

        List<EffectUnitStat> Effects = new ArrayList<EffectUnitStat>();

        Effects = AddEffects(Effects, unit, side);

        Effects.sort(new EffectUnitStat());

        for (EffectUnitStat item : Effects) {
            if (item.stat.val != 0) {
                if (AffectsThisUnit(item.effect, this, item.source)) {
                    item.effect.TryModifyEffect(this, item.source, item.stat, item.stat.GetStat());
                }

            }
        }

        return this;
    }

    public boolean AffectsThisUnit(IStatEffect effect, EffectData data, Unit source) {

        boolean affects = false;

        if (effect.Side().equals(EffectSides.Target)) {
            affects = source.equals(data.targetUnit);

        } else {
            affects = source.equals(data.sourceUnit);
        }

        if (affects == false) {
            //System.out.println("works");
        }

        return affects;
    }

    private List<EffectUnitStat> AddEffects(List<EffectUnitStat> effects, Unit unit, EffectSides side) {
        if (unit != null) {

            for (IStatEffects stateffects : Stats.allPreGenMapStatLists.get(IStatEffects.class)) {

                if (stateffects.getEffect().Side().equals(side)) {

                    if (unit.hasStat(stateffects.GUID())) {

                        StatData stat = unit.peekAtStat((Stat) stateffects);

                        if (stat.val != 0) {
                            effects.add(new EffectUnitStat(stateffects.getEffect(), unit, stat));
                        }
                    }
                }

            }

        }

        return effects;
    }

}