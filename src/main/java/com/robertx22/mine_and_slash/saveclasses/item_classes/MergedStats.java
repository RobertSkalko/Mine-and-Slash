package com.robertx22.mine_and_slash.saveclasses.item_classes;

import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.IStatsContainer;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.ITooltipList;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import net.minecraft.util.text.ITextComponent;

import java.util.List;

public class MergedStats implements ITooltipList {

    public List<IStatsContainer> list;

    public MergedStats(List<IStatsContainer> list) {
        this.list = list;
    }

    @Override
    public List<ITextComponent> GetTooltipString(TooltipInfo info) {
        return null;
    }
}
