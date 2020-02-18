package com.robertx22.mine_and_slash.saveclasses.spells;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.types.generated.ElementalAttackDamage;
import com.robertx22.mine_and_slash.database.stats.types.generated.ElementalSpellDamage;
import com.robertx22.mine_and_slash.database.stats.types.offense.PhysicalDamage;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.ITooltipList;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.wrappers.SText;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Storable
public class SpellCalcData implements ITooltipList {

    public static SpellCalcData empty() {

        SpellCalcData d = new SpellCalcData();
        d.empty = true;

        return d;
    }

    @Store
    public boolean mergeTooltips = false;

    public static SpellCalcData allAttackAndSpellDamages(float attack, float spell, int base) {
        SpellCalcData data = new SpellCalcData();

        data.scalingValues.add(new ScalingStatCalc(PhysicalDamage.getInstance(), attack));
        new ElementalAttackDamage(Elements.Nature).generateAllSingleVariations()
            .forEach(x -> data.scalingValues.add(new ScalingStatCalc(x, attack)));

        new ElementalSpellDamage(Elements.Nature).generateAllSingleVariations()
            .forEach(x -> data.scalingValues.add(new ScalingStatCalc(x, spell)));

        data.mergeTooltips = true;
        data.baseValue = base;

        return data;
    }

    public static SpellCalcData one(Stat stat, float multi, int base) {
        SpellCalcData data = new SpellCalcData();

        data.scalingValues.add(new ScalingStatCalc(stat, multi));
        data.baseValue = base;

        return data;
    }

    public static <T extends Stat> SpellCalcData all(Collection<T> stats, float multi, int base) {
        SpellCalcData data = new SpellCalcData();

        for (Stat s : stats) {
            data.scalingValues.add(new ScalingStatCalc(s, multi));
        }

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
        return scalingValues.stream()
            .mapToDouble(x -> x.multi)
            .sum() / scalingValues.size();

    }

    @Store
    public List<ScalingStatCalc> scalingValues = new ArrayList<>();

    @Store
    public StatScaling baseScaling = StatScaling.NORMAL;

    private boolean empty = false;

    @Store
    public int baseValue = 0;

    public int getCalculatedBaseValue(EntityCap.UnitData data) {
        return (int) baseScaling.scale(baseValue, data.getLevel());
    }

    public int getCalculatedScalingValue(EntityCap.UnitData data) {
        return scalingValues.stream()
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
            if (mergeTooltips) {
                List<ScalingStatCalc> copy = new ArrayList<>(scalingValues);

                float multiAttack = 0;
                float totalAttack = 0;

                float multiSpell = 0;
                float totalSpell = 0;

                List<ScalingStatCalc> attacks = copy.stream()
                    .filter(x -> x.getStat() instanceof ElementalAttackDamage || x.getStat() instanceof PhysicalDamage)
                    .collect(Collectors.toList());

                List<ScalingStatCalc> spells = copy.stream()
                    .filter(x -> x.getStat() instanceof ElementalSpellDamage)
                    .collect(Collectors.toList());

                if (attacks.stream()
                    .allMatch(x -> x.multi == attacks.get(0).multi) && spells.stream()
                    .allMatch(x -> x.multi == spells.get(0).multi)) {

                    for (ScalingStatCalc x : attacks) {
                        multiAttack = x.multi;
                        totalAttack += x.getCalculatedValue(info.unitdata);
                    }
                    for (ScalingStatCalc x : spells) {
                        multiSpell = x.multi;
                        totalSpell += x.getCalculatedValue(info.unitdata);
                    }

                    list.addAll(scalingValues.get(0)
                        .getTooltipFor(multiAttack, totalAttack, new SText(TextFormatting.GOLD + "Weapon Damage"), Elements.Elemental));
                    list.addAll(scalingValues.get(0)
                        .getTooltipFor(multiSpell, totalSpell, new SText(TextFormatting.GOLD + "Spell Damage"), Elements.Elemental));

                } else {
                    scalingValues.forEach(x -> list.addAll(x.GetTooltipString(info)));
                }

            } else {
                scalingValues.forEach(x -> list.addAll(x.GetTooltipString(info)));
            }

            if (baseValue > 0) {
                list.add(new StringTextComponent(
                    TextFormatting.RED + "Base value: " + getCalculatedBaseValue(info.unitdata)));
            }
        }

        return list;
    }
}
