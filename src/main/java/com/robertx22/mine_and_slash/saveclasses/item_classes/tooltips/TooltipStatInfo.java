package com.robertx22.mine_and_slash.saveclasses.item_classes.tooltips;

import com.robertx22.mine_and_slash.database.MinMax;
import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.saveclasses.ExactStatData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.StatModData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.ITooltipList;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;
import net.minecraft.util.text.ITextComponent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TooltipStatInfo implements ITooltipList {

    @Nullable
    public StatRangeContext statRange;

    @Nonnull
    public Stat stat;

    public StatMod mod;

    public float firstValue;
    public float secondValue;

    @Nonnull
    public StatModTypes type;

    @Nonnull
    public TooltipInfo tooltipInfo;

    public TooltipStatInfo(StatModData data, TooltipInfo info) {
        this.mod = data.getStatMod();
        this.stat = data.getStatMod()
            .GetBaseStat();
        this.firstValue = data.getFirstValue(info.level);
        this.secondValue = data.getSecondValue(info.level);
        this.type = data.getStatMod()
            .getModType();
        this.tooltipInfo = info;
        this.statRange = new StatRangeContext(data, info.minmax, info.level);

    }

    public TooltipStatInfo(ExactStatData data, TooltipInfo info) {
        this.stat = data.getStat();
        this.firstValue = data.getFirstValue();
        this.secondValue = data.getSecondValue();
        this.type = data.getType();
        this.tooltipInfo = info;

    }

    public boolean shouldShowDescriptions() {
        return tooltipInfo.shouldShowDescriptions();
    }

    public boolean useInDepthStats() {
        return tooltipInfo.useInDepthStats();
    }

    public void combine(TooltipStatInfo another) {
        this.firstValue += another.firstValue;
        this.secondValue += another.secondValue;

        if (this.statRange != null && another.statRange != null) {

            this.statRange.minmax = new MinMax(
                statRange.minmax.min + another.statRange.minmax.min,
                statRange.minmax.max + another.statRange.minmax.max
            );

        }
    }

    public boolean canBeCombined(TooltipStatInfo another) {

        if (mod != null && mod.usesNumberRanges()) {
            return false;
        }
        if (another.mod != null && another.mod.usesNumberRanges()) {
            return false;
        }

        if (another.statRange == null && this.statRange != null) {
            return false;
        }
        if (another.statRange != null && this.statRange == null) {
            return false;
        }
        return stat.GUID()
            .equals(another.stat.GUID()) && type.equals(another.type);
    }

    @Override
    public List<ITextComponent> GetTooltipString(TooltipInfo info) {
        return stat.getTooltipList(this);
    }

    public static List<TooltipStatInfo> mergeDuplicates(List<TooltipStatInfo> duplicates) {

        List<TooltipStatInfo> list = new ArrayList<>();

        for (TooltipStatInfo duplicate : duplicates) {

            Optional<TooltipStatInfo> found = list.stream()
                .filter(x -> x.canBeCombined(duplicate))
                .findFirst();

            if (found.isPresent()) {
                found.get()
                    .combine(duplicate);

            } else {
                list.add(duplicate);
            }
        }

        return list;

    }

}
