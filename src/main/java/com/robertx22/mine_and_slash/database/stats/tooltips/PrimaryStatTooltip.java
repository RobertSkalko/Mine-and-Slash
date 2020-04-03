package com.robertx22.mine_and_slash.database.stats.tooltips;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.saveclasses.item_classes.tooltips.TooltipStatInfo;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.ArrayList;
import java.util.List;

public class PrimaryStatTooltip implements IStatTooltipType {

    public ITextComponent NameText(TooltipStatInfo info) {

        Stat stat = info.stat;

        ITextComponent str = new StringTextComponent("");

        if (info.type.equals(StatModTypes.Flat) && stat.IsPercent()) {
            str.appendSibling(Words.Flat.locName())
                .appendText(" ");
        }

        str.appendSibling(stat.locName());

        if (info.tooltipInfo.isSet == false) {
            return Styles.REDCOMP()
                .appendSibling(new StringTextComponent(" " + stat.getFormattedIcon() + " ").appendSibling(str)
                    .appendText(": "));
        } else {
            return Styles.GREENCOMP()
                .appendSibling(str.appendText(": "));
        }
    }

    @OnlyIn(Dist.CLIENT)
    public ITextComponent NameAndValueText(TooltipStatInfo info) {

        float v1 = info.firstValue;
        float v2 = info.secondValue;

        if (v1 == v2) {

            String test = v1 + "";

            boolean hasMinus = test.contains("-");

            String minusplus = "";

            if (!hasMinus && v1 < 0) {
                minusplus = "-";
            }

            return NameText(info).appendText(minusplus + info.stat.printValue(v1));
        } else {
            String minusplus = v1 > 0 ? "" : "-";

            String str = minusplus + info.stat.printValue(v1) + "-" + info.stat.printValue(v2);

            return NameText(info).appendText(str);
        }

    }

    @Override
    public List<ITextComponent> getTooltipList(TooltipStatInfo info) {

        List<ITextComponent> list = new ArrayList<ITextComponent>();

        StatModTypes type = info.type;
        Stat stat = info.stat;
        ITextComponent text = NameAndValueText(info);

        if (type == StatModTypes.Flat) {

            if (stat.IsPercent()) {
                text.appendText("%");
            }

        } else if (type == StatModTypes.Percent) {
            text.appendText("%");

            if (type.equals(StatModTypes.Percent) && stat.IsPercent()) {
                if (info.firstValue > 0) {
                    text.appendText(" ")
                        .appendSibling(Words.Increased.locName());
                } else {
                    text.appendText(" ")
                        .appendSibling(Words.Decreased.locName());
                }
            }

        } else {
            text.appendText("% ")
                .appendSibling(Words.Multi.locName());
        }

        if (info.useInDepthStats()) {
            if (info.statRange != null) {
                text.appendSibling(getNumberRanges(info.statRange));
            }
        }

        list.add(text);
        if (info.shouldShowDescriptions()) {
            list.addAll(info.stat.getCutDescTooltip());
        }
        return list;

    }

}
