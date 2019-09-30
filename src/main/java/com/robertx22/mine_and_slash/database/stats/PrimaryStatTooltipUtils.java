package com.robertx22.mine_and_slash.database.stats;

import com.robertx22.mine_and_slash.saveclasses.item_classes.tooltips.TooltipStatInfo;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.ArrayList;
import java.util.List;

public class PrimaryStatTooltipUtils {

    public static ITextComponent NameText(TooltipStatInfo info) {

        Stat stat = info.stat;

        ITextComponent str = new StringTextComponent("");

        if (info.type.equals(StatTypes.Flat) && stat.IsPercent()) {
            str.appendSibling(Words.Flat.locName()).appendText(" ");
        }

        str.appendSibling(stat.locName());

        if (info.tooltipInfo.isSet == false) {
            return Styles.REDCOMP()
                    .appendSibling(new StringTextComponent(" " + stat.getFormattedIcon() + " ")
                            .appendSibling(str)
                            .appendText(": "));
        } else {
            return Styles.GREENCOMP().appendSibling(str.appendText(": "));
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static ITextComponent NameAndValueText(TooltipStatInfo info) {

        float val = info.amount;

        String minusplus = val > 0 ? "+" : "";

        return NameText(info).appendText(minusplus + info.stat.printValue(info.amount));
    }

    @OnlyIn(Dist.CLIENT)
    public static List<ITextComponent> getTooltipList(TooltipStatInfo info) {

        List<ITextComponent> list = new ArrayList<ITextComponent>();

        StatTypes type = info.type;
        Stat stat = info.stat;
        ITextComponent text = NameAndValueText(info);

        if (type == StatTypes.Flat) {

            if (stat.IsPercent()) {
                text.appendText("%");
            }

        } else if (type == StatTypes.Percent) {
            text.appendText("%");

            if (type.equals(StatTypes.Percent) && stat.IsPercent()) {
                if (info.amount > 0) {
                    text.appendText(" ").appendSibling(Words.Increased.locName());
                } else {
                    text.appendText(" ").appendSibling(Words.Decreased.locName());
                }
            }

        } else {
            text.appendText("% ").appendSibling(Words.Multi.locName());
        }

        if (Screen.hasShiftDown() && info.tooltipInfo.isSet == false) {
            if (info.statRange != null) {
                text.appendSibling(NormalStatTooltipUtils.getNumberRanges(info.statRange));
            }
        }

        list.add(text);
        if (Screen.hasAltDown()) {
            list.add(Styles.BLUECOMP()
                    .appendText(" [")
                    .appendSibling(info.stat.locDesc().appendText("]")));
        }
        return list;

    }

}
