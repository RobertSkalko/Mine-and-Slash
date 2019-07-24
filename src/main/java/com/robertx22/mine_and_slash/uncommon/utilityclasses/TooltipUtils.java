package com.robertx22.mine_and_slash.uncommon.utilityclasses;

import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.List;

public class TooltipUtils {

    public static ITextComponent level(int lvl) {
        return new StringTextComponent(TextFormatting.YELLOW + "").appendSibling(Words.Level
                .locName()).appendText((": " + lvl));
    }

    public static List<ITextComponent> removeDoubleBlankLines(List<ITextComponent> list,
                                                              int minLinesCutAllBlanks) {

        List<ITextComponent> newt = new ArrayList();

        boolean lastIsEmpty = false;

        boolean alwaysRemoveEmpty = list.size() > minLinesCutAllBlanks;

        for (int i = 0; i < list.size(); i++) {

            if (list.get(i).getFormattedText().isEmpty() == false) {
                lastIsEmpty = false;
                newt.add(list.get(i));
            } else {

                if (lastIsEmpty == false || alwaysRemoveEmpty) {
                    newt.add(list.get(i));
                }

                lastIsEmpty = true;

            }
        }

        return newt;
    }

    public static ITextComponent rarity(Rarity rarity) {

        return (new StringTextComponent(rarity.textFormatColor() + "").appendSibling(Words.Rarity
                .locName()
                .appendText(": ")
                .appendSibling(rarity.locName())));
    }

    public static ITextComponent tier(int tier) {

        return Styles.YELLOWCOMP()
                .appendSibling(Words.Tier.locName())
                .appendText(": " + tier);

    }

}
