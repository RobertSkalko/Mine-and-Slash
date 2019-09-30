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

        StatMod mod = info.mod;
        Stat basestat = mod.GetBaseStat();

        ITextComponent str = new StringTextComponent("");

        if (mod.Type().equals(StatTypes.Flat) && basestat.IsPercent()) {
            str.appendSibling(Words.Flat.locName()).appendText(" ");
        }

        str.appendSibling(basestat.locName());

        if (info.tooltipInfo.isSet == false) {
            return Styles.REDCOMP()
                    .appendSibling(new StringTextComponent(" " + basestat.getFormattedIcon() + " ")
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
        StatMod mod = info.mod;
        Stat basestat = mod.GetBaseStat();
        ITextComponent text = NameAndValueText(info);

        if (mod.Type() == StatTypes.Flat) {

            if (basestat.IsPercent()) {
                text.appendText("%");
            }

        } else if (mod.Type() == StatTypes.Percent) {
            text.appendText("%");

            if (mod.Type().equals(StatTypes.Percent) && basestat.IsPercent()) {
                if (info.modData.GetActualVal(info.level) > 0) {
                    text.appendText(" ").appendSibling(Words.Increased.locName());
                } else {
                    text.appendText(" ").appendSibling(Words.Decreased.locName());
                }
            }

        } else {
            text.appendText("% ").appendSibling(Words.Multi.locName());
        }

        if (Screen.hasShiftDown() && info.tooltipInfo.isSet == false) {

            if (info.mod != null) {
                text.appendSibling(NormalStatTooltipUtils.getNumberRanges(info.modData, info.minmax, info.level));
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
