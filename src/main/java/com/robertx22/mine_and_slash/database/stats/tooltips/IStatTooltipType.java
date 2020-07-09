package com.robertx22.mine_and_slash.database.stats.tooltips;

import com.robertx22.mine_and_slash.saveclasses.item_classes.tooltips.TooltipStatInfo;
import net.minecraft.util.text.ITextComponent;

import java.util.List;

public interface IStatTooltipType {
    List<ITextComponent> getTooltipList(TooltipStatInfo info);

}
