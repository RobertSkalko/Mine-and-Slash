package com.robertx22.mine_and_slash.database.stats;

import com.robertx22.mine_and_slash.saveclasses.gearitem.StatModData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
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

    public static ITextComponent NameText(TooltipInfo info, StatModData data) {

        StatMod mod = data.getStatMod();
        Stat basestat = mod.GetBaseStat();

        ITextComponent str = new StringTextComponent("");

        if (mod.Type().equals(StatTypes.Flat) && basestat.IsPercent()) {
            str.appendSibling(Words.Flat.locName()).appendText(" ");
        }

        str.appendSibling(basestat.locName());

        if (info.isSet == false) {
            return Styles.REDCOMP()
                    .appendSibling(new StringTextComponent(" " + basestat.getFormattedIcon() + " ")
                            .appendSibling(str)
                            .appendText(": "));
        } else {
            return Styles.GREENCOMP().appendSibling(str.appendText(": "));
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static ITextComponent NameAndValueText(Stat stat, TooltipInfo info,
                                                  StatModData data) {

        float val = data.GetActualVal(info.level);

        String minusplus = val > 0 ? "+" : "";

        return NameText(info, data).appendText(minusplus + stat.printValue(data, info.level));
    }

    @OnlyIn(Dist.CLIENT)
    public static List<ITextComponent> getTooltipList(Stat stat, TooltipInfo info,
                                                      StatModData data) {

        List<ITextComponent> list = new ArrayList<ITextComponent>();
        StatMod mod = data.getStatMod();
        Stat basestat = mod.GetBaseStat();
        ITextComponent text = NameAndValueText(stat, info, data);

        if (mod.Type() == StatTypes.Flat) {

            if (basestat.IsPercent()) {
                text.appendText("%");
            }

        } else if (mod.Type() == StatTypes.Percent) {
            text.appendText("%");

            if (mod.Type().equals(StatTypes.Percent) && basestat.IsPercent()) {
                if (data.GetActualVal(info.level) > 0) {
                    text.appendText(" ").appendSibling(Words.Increased.locName());
                } else {
                    text.appendText(" ").appendSibling(Words.Decreased.locName());
                }
            }

        } else {
            text.appendText("% ").appendSibling(Words.Multi.locName());
        }

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

}
