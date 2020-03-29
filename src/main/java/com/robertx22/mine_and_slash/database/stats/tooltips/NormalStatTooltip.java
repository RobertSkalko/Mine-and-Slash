package com.robertx22.mine_and_slash.database.stats.tooltips;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.saveclasses.item_classes.tooltips.TooltipStatInfo;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.List;

public class NormalStatTooltip implements IStatTooltipType {

    @Override
    public List<ITextComponent> getTooltipList(TooltipStatInfo info) {

        List<ITextComponent> list = new ArrayList<ITextComponent>();

        ITextComponent text = getValueComp(info).appendText(" ")
            .appendSibling(getStatComp(info))
            .appendSibling(StatModTypes.getSuffix(info.type));

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

    private ITextComponent getStatComp(TooltipStatInfo info) {

        Stat stat = info.stat;

        ITextComponent str = new StringTextComponent("");

        if (info.type.equals(StatModTypes.Percent) && stat.IsPercent()) {
            if (info.amount > 0) {
                str.appendSibling(Words.Increased.locName());
            } else {
                str.appendSibling(Words.Decreased.locName());
            }

            str.appendText(" ");
        }

        str.appendSibling(stat.locName());

        if (info.stat.getElement() != null && info.stat.getElement() != Elements.Physical) { // todo unsure if good
            str.appendText("" + info.stat.getElement().format + " (" + info.stat.getElement().icon + ")" + TextFormatting.GRAY + "");
        }

        if (info.tooltipInfo.isSet == false) {
            return Styles.GRAYCOMP()
                .appendSibling(str);
        } else {
            return Styles.GREENCOMP()
                .appendSibling(str);
        }

    }

    private ITextComponent getValueComp(TooltipStatInfo info) {

        float val = info.amount;
        String minusplus = val > 0 ? "+" : "";

        TextFormatting color = TextFormatting.GREEN;

        if (val < 0) {
            color = TextFormatting.RED;
        }

        ITextComponent comp = new StringTextComponent("");
        comp.appendText(color + minusplus + info.stat.printValue(info.amount));
        comp.appendSibling(StatModTypes.getNumberSuffix(info.type, info.stat));
        comp.appendText(TextFormatting.RESET + "");
        return comp;

    }
}
