package com.robertx22.mine_and_slash.database.stats.tooltips;

import com.robertx22.mine_and_slash.saveclasses.item_classes.tooltips.TooltipStatInfo;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.List;

public class BaseLocalStatTooltip implements IStatTooltipType {

    @Override
    public List<ITextComponent> getTooltipList(TooltipStatInfo info) {

        List<ITextComponent> list = new ArrayList<ITextComponent>();

        list.add(new StringTextComponent(TextFormatting.GRAY + info.stat.getStatNameRegex()
            .translate(info.type, info.firstValue, info.secondValue, info.stat)));

        /*
        if (info.useInDepthStats()) {
            if (info.statRange != null) {
                text.appendSibling(getNumberRanges(info.statRange));
            }
        }
                 */
        if (info.shouldShowDescriptions()) {
            list.addAll(info.stat.getCutDescTooltip());
        }

        return list;

    }
}
