package com.robertx22.mine_and_slash.saveclasses.gearitem;

import com.robertx22.mine_and_slash.database.MinMax;
import com.robertx22.mine_and_slash.database.rarities.IStatPercents;
import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.item_classes.tooltips.TooltipStatInfo;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.text.DecimalFormat;
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
        Add(unit.getUnit()
            .getCreateStat(this.getStatMod()
                .GetBaseStat()), level);
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

    public float GetActualVal(int level) {

        StatMod mod = getStatMod();

        Stat stat = mod.GetBaseStat();

        float val;

        if (useMinimum) {
            val = mod.getFloatByPercent(percent);
        } else {
            val = mod.getFloatByPercentWithoutMin(percent);
        }

        if (mod.getModType()
            .equals(StatModTypes.Flat)) {
            val = stat.calculateScalingStatGrowth(val, level);
        }

        return val;
    }

    public String printValue(int level) {

        float val = GetActualVal(level);

        DecimalFormat format = new DecimalFormat();

        if (Math.abs(val) < 10) {
            format.setMaximumFractionDigits(1);

            return format.format(val);

        } else {
            int intval = (int) val;
            return intval + "";
        }

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

    public void Add(StatData data, int level) {

        StatModTypes type = getType();

        if (type == StatModTypes.Flat) {
            data.Flat += GetActualVal(level);
        } else if (type == StatModTypes.Percent) {
            data.Percent += GetActualVal(level);
        } else if (type == StatModTypes.Multi) {
            data.Multi += GetActualVal(level);

        }

    }

}