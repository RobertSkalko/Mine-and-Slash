package com.robertx22.mine_and_slash.database.stats.tooltips;

import com.robertx22.mine_and_slash.database.MinMax;
import com.robertx22.mine_and_slash.saveclasses.gearitem.StatModData;
import com.robertx22.mine_and_slash.saveclasses.item_classes.tooltips.StatRangeContext;
import com.robertx22.mine_and_slash.saveclasses.item_classes.tooltips.TooltipStatInfo;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.List;

public interface IStatTooltipType {
    List<ITextComponent> getTooltipList(TooltipStatInfo info);

    default ITextComponent getNumberRanges(StatRangeContext ctx) {

        MinMax minmax = ctx.minmax;
        StatModData data = ctx.modData;
        int level = ctx.level;

        String str = "";

        StatModData min = StatModData.Load(data.getStatMod(), minmax.min);
        StatModData max = StatModData.Load(data.getStatMod(), minmax.max);

        if (data.getStatMod()
            .usesNumberRanges()) {
            str = " (" + min.getFirstValue(level) + " - " + max.getFirstValue(level) + ")/";
            str += " (" + min.getSecondValue(level) + " - " + max.getSecondValue(level) + ")";

        } else {
            str = " (" + min.printValue(level) + " - " + max.printValue(level) + ")";
        }

        return Styles.GREENCOMP()
            .appendSibling(
                new StringTextComponent(str));

    }
}
