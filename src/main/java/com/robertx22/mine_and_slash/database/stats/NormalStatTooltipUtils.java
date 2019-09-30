package com.robertx22.mine_and_slash.database.stats;

import com.robertx22.mine_and_slash.database.MinMax;
import com.robertx22.mine_and_slash.saveclasses.gearitem.StatModData;
import com.robertx22.mine_and_slash.saveclasses.item_classes.tooltips.TooltipStatInfo;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.List;

public class NormalStatTooltipUtils {

    public static List<ITextComponent> getTooltipList(TooltipStatInfo info) {

        List<ITextComponent> list = new ArrayList<ITextComponent>();

        ITextComponent text = getValueComp(info).appendText(" ")
                .appendSibling(getStatComp(info))
                .appendSibling(StatTypes.getSuffix(info.type));

        if (Screen.hasShiftDown() && info.tooltipInfo.isSet == false) {
            text.appendSibling(getNumberRanges(info.modData, info.minmax, info.level));
        }

        list.add(text);

        if (Screen.hasAltDown()) {
            list.add(Styles.BLUECOMP()
                    .appendText(" [")
                    .appendSibling(info.stat.locDesc().appendText("]")));
        }
        return list;

    }

    public static ITextComponent getNumberRanges(StatModData data, MinMax minmax,
                                                 int level) {
        StatModData min = StatModData.Load(data.getStatMod(), minmax.Min);
        StatModData max = StatModData.Load(data.getStatMod(), minmax.Max);

        ITextComponent extraInfo = Styles.GREENCOMP()
                .appendSibling(new StringTextComponent(" (" + min.printValue(level) + " - " + max
                        .printValue(level) + ")"));

        return extraInfo;

    }

    public static ITextComponent getStatComp(TooltipStatInfo info) {

        StatMod mod = info.mod;
        Stat basestat = mod.GetBaseStat();

        ITextComponent str = new StringTextComponent("");

        if (mod.Type().equals(StatTypes.Percent) && basestat.IsPercent()) {
            if (info.amount > 0) {
                str.appendSibling(Words.Increased.locName());
            } else {
                str.appendSibling(Words.Decreased.locName());
            }

            str.appendText(" ");
        }

        str.appendSibling(basestat.locName());

        if (info.tooltipInfo.isSet == false) {
            return Styles.GRAYCOMP().appendSibling(str);
        } else {
            return Styles.GREENCOMP().appendSibling(str);
        }
    }

    public static ITextComponent getValueComp(TooltipStatInfo info) {

        float val = info.amount;
        String minusplus = val > 0 ? "+" : "";

        TextFormatting color = TextFormatting.GREEN;

        if (val < 0) {
            color = TextFormatting.RED;
        }

        ITextComponent comp = new StringTextComponent("");
        comp.appendText(color + minusplus + info.stat.printValue(info.amount));
        comp.appendSibling(StatTypes.getNumberSuffix(info.type, info.stat));
        comp.appendText(TextFormatting.RESET + "");
        return comp;

    }
}
