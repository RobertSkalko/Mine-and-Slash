package com.robertx22.mine_and_slash.database.stats;

import com.robertx22.mine_and_slash.saveclasses.gearitem.StatModData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
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

    public static List<ITextComponent> getTooltipList(Stat stat, TooltipInfo info,
                                                      StatModData data) {

        List<ITextComponent> list = new ArrayList<ITextComponent>();
        StatMod mod = data.getStatMod();
        Stat basestat = mod.GetBaseStat();
        ITextComponent text = getValueComp(stat, info, data).appendText(" ")
                .appendSibling(getStatComp(info, data));

        if (Screen.hasShiftDown() && info.isSet == false) {

            StatModData min = StatModData.Load(data.getStatMod(), info.minmax.Min);
            StatModData max = StatModData.Load(data.getStatMod(), info.minmax.Max);

            ITextComponent extraInfo = Styles.GREENCOMP()
                    .appendSibling(new StringTextComponent(" (" + min.printValue(info.level) + " - " + max
                            .printValue(info.level) + ")"));

            text.appendSibling(extraInfo);
        }

        list.add(text);
        if (Screen.hasAltDown()) {
            list.add(Styles.BLUECOMP()
                    .appendText(" [")
                    .appendSibling(stat.locDesc().appendText("]")));
        }
        return list;

    }

    public static ITextComponent getStatComp(TooltipInfo info, StatModData data) {

        StatMod mod = data.getStatMod();
        Stat basestat = mod.GetBaseStat();

        ITextComponent str = new StringTextComponent("");

        if (mod.Type().equals(StatTypes.Percent) && basestat.IsPercent()) {
            if (data.GetActualVal(info.level) > 0) {
                str.appendSibling(Words.Increased.locName());
            } else {
                str.appendSibling(Words.Decreased.locName());
            }

            str.appendText(" ");
        }

        str.appendSibling(basestat.locName());

        if (info.isSet == false) {
            return Styles.GRAYCOMP().appendSibling(str);
        } else {
            return Styles.GREENCOMP().appendSibling(str);
        }
    }

    public static ITextComponent getValueComp(Stat stat, TooltipInfo info,
                                              StatModData data) {

        float val = data.GetActualVal(info.level);
        String minusplus = val > 0 ? "+" : "";

        ITextComponent comp = new StringTextComponent("");
        comp.appendText(TextFormatting.GREEN + minusplus + stat.printValue(data, info.level));
        comp.appendSibling(StatTypes.getSuffix(data.getStatMod()));
        comp.appendText(TextFormatting.RESET + "");
        return comp;

    }
}
