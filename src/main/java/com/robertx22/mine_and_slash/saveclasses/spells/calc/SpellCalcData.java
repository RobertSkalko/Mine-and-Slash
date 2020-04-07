package com.robertx22.mine_and_slash.saveclasses.spells.calc;

import com.robertx22.mine_and_slash.database.stats.types.generated.ElementalAttackDamage;
import com.robertx22.mine_and_slash.database.stats.types.offense.PhysicalDamage;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.ITooltipList;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.StatScaling;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.wrappers.SText;
import info.loenwind.autosave.annotations.Factory;
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

    public static SpellCalcData base(float base) {
        SpellCalcData data = new SpellCalcData();

        data.baseValue = base;

        return data;
    }

    public static SpellCalcData scaleWithAttack(float attack, float base) {
        SpellCalcData data = new SpellCalcData();

        data.scalingValues.add(new ScalingStatCalc(PhysicalDamage.getInstance(), attack));
        data.mergedScalingValues.add(new MergedScalingStatsCalc(new ElementalAttackDamage(Elements.Nature).generateAllSingleVariations(), attack, new SText(TextFormatting.GOLD + "Weapon Damages")));

        data.baseValue = base;

        return data;
    }

    @Factory
    private SpellCalcData() {

    }

    public SpellCalcData(ScalingStatCalc calc, int base) {
        this.scalingValues.add(calc);
        this.baseValue = base;
    }

    public double getScalingMultiAverage() {
        return getAllScalingValues().stream()
            .mapToDouble(x -> x.getMulti())
            .sum() / scalingValues.size();

    }

    public List<BaseStatCalc> getAllScalingValues() {
        List<BaseStatCalc> list = new ArrayList<>();
        list.addAll(scalingValues);
        list.addAll(mergedScalingValues);

        return list;
    }

    @Store
    public List<ScalingStatCalc> scalingValues = new ArrayList<>();

    @Store
    public List<MergedScalingStatsCalc> mergedScalingValues = new ArrayList<>();

    @Store
    public StatScaling baseScaling = StatScaling.NORMAL;

    private boolean empty = false;

    @Store
    public float baseValue = 0;

    public int getCalculatedBaseValue(EntityCap.UnitData data) {
        return (int) baseScaling.scale(baseValue, data.getLevel());
    }

    public int getCalculatedScalingValue(EntityCap.UnitData data) {
        return getAllScalingValues().stream()
            .mapToInt(x -> x.getCalculatedValue(data))
            .sum();
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
            getAllScalingValues().forEach(x -> list.addAll(x.GetTooltipString(info)));

            if (baseValue > 0) {
                list.add(new StringTextComponent(
                    TextFormatting.RED + "Base value: " + getCalculatedBaseValue(info.unitdata)));
            }
        }

        return list;
    }
}
