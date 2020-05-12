package com.robertx22.mine_and_slash.saveclasses.item_classes.tooltips;

import com.robertx22.mine_and_slash.saveclasses.gearitem.StatModData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.IGearPartTooltip;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.IStatModsContainer.LevelAndStats;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;
import net.minecraft.util.text.ITextComponent;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MergedStats implements IGearPartTooltip {

    public List<TooltipStatInfo> list;

    public MergedStats(List<LevelAndStats> list, TooltipInfo info) {

        List<TooltipStatInfo> infolist = new ArrayList<>();

        for (LevelAndStats part : list) {
            for (StatModData mod : part.mods) {
                infolist.add(new TooltipStatInfo(mod, info));
            }
        }

        this.list = TooltipStatInfo.mergeDuplicates(infolist);

        this.list.sort(Comparator.comparingInt(x -> -(int) (x.firstValue + x.secondValue)));
        this.list.sort(Comparator.comparing(x -> !x.mod.getModType()
            .equals(StatModTypes.Flat) && x.stat.IsPercent()));
    }

    @Override
    public List<ITextComponent> GetTooltipString(TooltipInfo info, GearItemData gear) {

        List<ITextComponent> tooltip = new ArrayList<>();

        list.forEach(x -> tooltip.addAll(x.GetTooltipString(info)));
        return tooltip;
    }

    @Override
    public Part getPart() {
        return Part.OTHER;
    }
}
