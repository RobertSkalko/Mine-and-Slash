package com.robertx22.mine_and_slash.uncommon.effectdatas;

import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.saveclasses.Unit;
import com.robertx22.mine_and_slash.spells.bases.BaseSpell;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.IBuffableSpell;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect.EffectSides;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffects;
import net.minecraft.entity.LivingEntity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SpellBuffEffect extends EffectData implements IBuffableSpell {

    public IBuffableSpell buffable;

    private SpellBuffType buff = SpellBuffType.None;

    public SpellBuffEffect(LivingEntity source, IBuffableSpell buffable) {

        super(source, null);
        this.canceled = false;
        // a bit funky, didn't expect effects with just 1 entity

        this.buffable = buffable;

        this.source = source;

        this.sourceData = Load.Unit(source);

        try {
            sourceUnit = sourceData.getUnit();

            if (sourceUnit != null) {
                sourceData.recalculateStats(source);

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

        if (source == null || canceled == true || sourceUnit == null || sourceData == null)
            return;

        TryApplyEffects(this.GetSource());

        if (this.canceled != true) {

            sourceData.setUnit(sourceUnit, source);

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
            for (StatData stat : unit.getStats().values()) {
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
    public void setBuffType(BaseSpell.SpellType type) {
        this.buffable.setBuffType(type);

    }

    @Override
    public BaseSpell.SpellType getBuffType() {
        return this.buffable.getBuffType();
    }

}
