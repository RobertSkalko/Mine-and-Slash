package com.robertx22.mine_and_slash.saveclasses.item_classes.tooltips;

import com.robertx22.mine_and_slash.database.MinMax;
import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.saveclasses.gearitem.StatModData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.ITooltipList;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;
import net.minecraft.util.text.ITextComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TooltipStatInfo implements ITooltipList {

    public TooltipStatInfo(StatModData data, TooltipInfo info) {
        this.mod = data.getStatMod();
        this.stat = data.getStatMod().GetBaseStat();
        this.amount = data.GetActualVal(info.level);
        this.type = data.getStatMod().Type();
        this.minmax = info.minmax;
        this.tooltipInfo = info;
        this.modData = data;
        this.level = info.level;
    }

    public void combine(TooltipStatInfo another) {
        this.amount += another.amount;
        this.minmax.Min += another.minmax.Min;
        this.minmax.Max += another.minmax.Max;
    }

    public boolean canBeCombined(TooltipStatInfo another) {

        return stat.GUID().equals(another.stat.GUID()) && type.equals(another.type);
    }

    public StatModData modData;
    public StatMod mod;
    public Stat stat;
    public float amount;
    public StatTypes type;
    public MinMax minmax;
    public TooltipInfo tooltipInfo;
    public int level;

    @Override
    public List<ITextComponent> GetTooltipString(TooltipInfo info) {
        return stat.getTooltipList(this);
    }

    public static List<TooltipStatInfo> mergeDuplicates(
            List<TooltipStatInfo> duplicates) {

        List<TooltipStatInfo> list = new ArrayList<>();

        for (TooltipStatInfo duplicate : duplicates) {

            Optional<TooltipStatInfo> found = list.stream()
                    .filter(x -> x.canBeCombined(duplicate))
                    .findFirst();

            if (found.isPresent()) {
                found.get().combine(duplicate);

            } else {
                list.add(duplicate);
            }
        }

        return list;

    }

}
