package com.robertx22.mine_and_slash.database.stats;

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
        StatMod mod = info.mod;
        Stat basestat = mod.GetBaseStat();
        ITextComponent text = getValueComp(info).appendText(" ")
                .appendSibling(getStatComp(info));

        if (Screen.hasShiftDown() && info.tooltipInfo.isSet == false) {

            StatModData min = StatModData.Load(mod, info.minmax.Min);
            StatModData max = StatModData.Load(mod, info.minmax.Max);

            ITextComponent extraInfo = Styles.GREENCOMP()
                    .appendSibling(new StringTextComponent(" (" + min.printValue(info.level) + " - " + max
                            .printValue(info.level) + ")"));

            text.appendSibling(extraInfo);
        }

        list.add(text);
        if (Screen.hasAltDown()) {
            list.add(Styles.BLUECOMP()
                    .appendText(" [")
                    .appendSibling(info.stat.locDesc().appendText("]")));
        }
        return list;

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

        ITextComponent comp = new StringTextComponent("");
        comp.appendText(TextFormatting.GREEN + minusplus + info.stat.printValue(info.amount));
        comp.appendSibling(StatTypes.getSuffix(info.mod));
        comp.appendText(TextFormatting.RESET + "");
        return comp;

    }
}
