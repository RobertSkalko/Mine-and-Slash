package com.robertx22.mine_and_slash.saveclasses.item_classes;

import com.robertx22.mine_and_slash.saveclasses.gearitem.StatModData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.IStatsContainer.LevelAndStats;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.ITooltipList;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import net.minecraft.util.text.ITextComponent;

import java.util.ArrayList;
import java.util.List;

public class MergedStats implements ITooltipList {

    public List<LevelAndStats> list;

    public MergedStats(List<LevelAndStats> list) {
        this.list = list;
    }

    @Override
    public List<ITextComponent> GetTooltipString(TooltipInfo info) {

        List<ITextComponent> tooltip = new ArrayList<>();

        for (LevelAndStats x : list) {
            for (StatModData y : x.mods) {
                tooltip.addAll(y.GetTooltipString(info.withLevel(x.level)));
            }
        }

        return tooltip;
    }
}
