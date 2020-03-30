package com.robertx22.mine_and_slash.saveclasses.gearitem;

import com.robertx22.mine_and_slash.database.MinMax;
import com.robertx22.mine_and_slash.database.rarities.IStatPercents;
import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.item_classes.tooltips.TooltipStatInfo;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.Arrays;
import java.util.List;

@Storable
public class StatModData extends BaseStatContainer {

    public StatModData() {

    }

    public static StatModData NewRandom(IStatPercents rar, StatMod mod) {

        StatModData data = new StatModData();

        data.baseModName = mod.GUID();
        data.randomize(rar);

        return data;
    }

    public static StatModData NewRandom(MinMax minmax, StatMod mod) {

        StatModData data = new StatModData();

        data.baseModName = mod.GUID();
        data.randomize(minmax);

        return data;
    }

    public static StatModData Load(StatMod mod, int percent) {

        StatModData data = new StatModData();

        data.baseModName = mod.GUID();
        data.percent = percent;

        return data;
    }

    @Override
    public void applyStats(UnitData unit, int level) {
        unit.getUnit()
            .getCreateStat(this.getStatMod()
                .GetBaseStat())
            .add(this, level);
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int perc) {
        this.percent = perc;
    }

    public void setPercentClamp(MinMax minmax, int perc) {
        this.percent = MathHelper.clamp(perc, minmax.min, minmax.max);
    }

    @Store
    private int percent;

    @Store
    private String baseModName;

    private transient boolean useMinimum = true;

    public StatModData noMin() {
        this.useMinimum = false;
        return this;
    }

    public boolean canBeMerged(StatModData mod) {
        return baseModName == mod.baseModName;
    }

    public StatModTypes getType() {
        return getStatMod().getModType();
    }

    public StatMod getStatMod() {
        return SlashRegistry.StatMods()
            .get(baseModName);

    }

    public enum FirstSecond {
        FIRST, SECOND
    }

    public float getFirstValue(int level) {
        return getActualValue(level, FirstSecond.FIRST);
    }

    public float getSecondValue(int level) {
        return getActualValue(level, FirstSecond.SECOND);
    }

    public float getActualValue(int level, FirstSecond firstOrSecond) {

        StatMod mod = getStatMod();
        Stat stat = mod.GetBaseStat();

        float val;

        if (useMinimum) {
            if (firstOrSecond.equals(FirstSecond.FIRST)) {
                val = getFirstActualValue();
            } else {
                val = getSecondActualValue();
            }

        } else {
            if (firstOrSecond.equals(FirstSecond.FIRST)) {
                val = getFirstActualValueWithoutMin();
            } else {
                val = getSecondActualValueWithoutMin();
            }

        }

        if (mod.getModType()
            .equals(StatModTypes.Flat)) {
            val = stat.calculateScalingStatGrowth(val, level);
        }

        return val;
    }

    private float getFirstActualValue() {
        StatMod mod = getStatMod();
        return (mod.getMin() + (mod.getMax() - mod.getMin()) * percent / 100);
    }

    private float getFirstActualValueWithoutMin() {
        StatMod mod = getStatMod();
        return (mod.getMax() * percent / 100);

    }

    private float getSecondActualValue() {
        StatMod mod = getStatMod();
        return (mod.getMinSecond() + (mod.getMaxSecond() - mod.getMinSecond()) * percent / 100);
    }

    private float getSecondActualValueWithoutMin() {
        StatMod mod = getStatMod();
        return (mod.getMaxSecond() * percent / 100);
    }

    @Override
    public List<ITextComponent> GetTooltipString(TooltipInfo info) {

        try {
            return getStatMod().GetBaseStat()
                .getTooltipList(new TooltipStatInfo(this, info));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Arrays.asList(new StringTextComponent(""));
    }

    public void randomize(IStatPercents rarity) {
        this.randomize(rarity.StatPercents());
    }

    public void randomize(MinMax minmax) {
        this.percent = minmax.random();
    }

}