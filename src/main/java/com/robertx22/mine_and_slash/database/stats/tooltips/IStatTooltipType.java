package com.robertx22.mine_and_slash.database.stats.tooltips;

import com.robertx22.mine_and_slash.database.MinMax;
import com.robertx22.mine_and_slash.saveclasses.gearitem.StatModData;
import com.robertx22.mine_and_slash.saveclasses.item_classes.tooltips.StatRangeContext;
import com.robertx22.mine_and_slash.saveclasses.item_classes.tooltips.TooltipStatInfo;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.List;

import static com.robertx22.mine_and_slash.uncommon.utilityclasses.NumberUtils.trimFloat;

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
            str = " (" + trimFloat(min.getFirstValue(level)) + " - " + trimFloat(max.getFirstValue(level)) + ")/";
            str += " (" + trimFloat(min.getSecondValue(level)) + " - " + trimFloat(max.getSecondValue(level)) + ")";

        } else {

            str = " (" + trimFloat(min.getFirstValue(level)) + " - " + trimFloat(max.getFirstValue(level)) + ")";
        }

        return Styles.GREENCOMP()
            .appendSibling(
                new StringTextComponent(str));

    }
}
