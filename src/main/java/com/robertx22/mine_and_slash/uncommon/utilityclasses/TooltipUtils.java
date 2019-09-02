package com.robertx22.mine_and_slash.uncommon.utilityclasses;

import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.List;

public class TooltipUtils {

    public static String CHECKMARK = TextFormatting.GREEN + "\u2714";
    public static String X = TextFormatting.RED + "\u2716";

    public static ITextComponent level(int lvl) {
        return new StringTextComponent(TextFormatting.YELLOW + "").appendSibling(Words.Level
                .locName()).appendText((": " + lvl));

    }

    public static List<String> cutIfTooLong(String str) {

        List<String> list = new ArrayList<>();

        int start = 0;
        int i = 0;
        for (Character c : str.toCharArray()) {

            if (i == str.length() - 1) {
                list.add(str.substring(start));
            } else if (i - start > 25 && c == ' ') {
                String cut = str.substring(start, i);
                if (start > 0) {
                    cut = cut.substring(1);
                }

                list.add(cut);

                start = i;
            }
            i++;
        }

        return list;
    }

    public static ITextComponent lvlReq(int lvl, EntityCap.UnitData player) {

        ITextComponent comp;

        if (player.getLevel() >= lvl) {
            comp = new StringTextComponent(CHECKMARK);
        } else {
            comp = new StringTextComponent(X);
        }

        return comp.appendSibling(new StringTextComponent(TextFormatting.GRAY + " ").appendText("Lvl Req: " + lvl));

    }

    public static List<ITextComponent> removeDoubleBlankLines(List<ITextComponent> list,
                                                              int minLinesCutAllBlanks) {

        List<ITextComponent> newt = new ArrayList();

        boolean lastIsEmpty = false;

        boolean alwaysRemoveEmpty = list.size() > minLinesCutAllBlanks;

        for (int i = 0; i < list.size(); i++) {

            if (list.get(i).getFormattedText().length() > 2) {
                lastIsEmpty = false;
                newt.add(list.get(i));
            } else {

                if (lastIsEmpty || alwaysRemoveEmpty) {

                } else {
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
