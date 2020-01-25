package com.robertx22.mine_and_slash.saveclasses.spells;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.ITooltipList;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.List;

@Storable
public class SpellCalcData implements ITooltipList {

    public static SpellCalcData empty() {

        SpellCalcData d = new SpellCalcData();
        d.empty = true;

        return d;
    }

    public static SpellCalcData one(Stat stat, float multi, int base) {
        SpellCalcData data = new SpellCalcData();

        data.scalingValues.add(new ScalingStatCalc(stat, multi));
        data.baseValue = base;

        return data;
    }

    private SpellCalcData() {

    }

    public SpellCalcData(ScalingStatCalc calc, int base) {
        this.scalingValues.add(calc);
        this.baseValue = base;
    }

    public double getScalingMultiAverage() {
        return scalingValues.stream().mapToDouble(x -> x.multi).sum() / scalingValues.size();

    }

    @Store
    public List<ScalingStatCalc> scalingValues = new ArrayList<>();

    @Store
    public StatScaleType baseScaling = StatScaleType.NORMAL;

    private boolean empty = false;

    @Store
    public int baseValue = 0;

    public int getCalculatedBaseValue(EntityCap.UnitData data) {
        return (int) baseScaling.scale(baseValue, data.getLevel());
    }

    public int getCalculatedScalingValue(EntityCap.UnitData data) {
        return scalingValues.stream().mapToInt(x -> x.getCalculatedValue(data)).sum();
    }

    public int getCalculatedValue(EntityCap.UnitData data) {
        int val = getCalculatedScalingValue(data);
        val += getCalculatedBaseValue(data);
        return val;

    }

    @Override
    public List<ITextComponent> GetTooltipString(TooltipInfo info) {

        List<ITextComponent> list = new ArrayList<>();

        if (!empty) {
            scalingValues.forEach(x -> list.addAll(x.GetTooltipString(info)));

            if (baseValue > 0) {
                list.add(new StringTextComponent(
                        TextFormatting.RED + "Base value: " + getCalculatedBaseValue(info.unitdata)));
            }
        }

        return list;
    }
}
