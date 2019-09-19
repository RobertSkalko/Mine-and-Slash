package com.robertx22.mine_and_slash.saveclasses.item_classes.tooltips;

import com.robertx22.mine_and_slash.saveclasses.gearitem.StatModData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.IStatModsContainer.LevelAndStats;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.ITooltipList;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import net.minecraft.util.text.ITextComponent;

import java.util.ArrayList;
import java.util.List;

public class MergedStats implements ITooltipList {

    public List<TooltipStatInfo> list;

    public MergedStats(List<LevelAndStats> list, TooltipInfo info) {

        List<TooltipStatInfo> infolist = new ArrayList<>();

        for (LevelAndStats part : list) {
            for (StatModData mod : part.mods) {
                infolist.add(new TooltipStatInfo(mod, info));
            }
        }

        this.list = TooltipStatInfo.mergeDuplicates(infolist);
    }

    @Override
    public List<ITextComponent> GetTooltipString(TooltipInfo info) {

        List<ITextComponent> tooltip = new ArrayList<>();
        list.forEach(x -> tooltip.addAll(x.GetTooltipString(info)));
        return tooltip;
    }
}
