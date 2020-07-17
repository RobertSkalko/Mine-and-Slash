package com.robertx22.mine_and_slash.uncommon.utilityclasses;

import com.robertx22.mine_and_slash.database.data.rarities.gears.UniqueGear;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.ICommonDataItem;
import com.robertx22.mine_and_slash.uncommon.localization.CLOC;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.wrappers.SText;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TooltipUtils {

    public static String CHECKMARK = TextFormatting.GREEN + "\u2714";
    public static String X = TextFormatting.RED + "\u2716";

    public static ITextComponent color(TextFormatting format, ITextComponent comp) {
        return new StringTextComponent(format + "").appendSibling(comp);
    }

    public static void addEmpty(List<ITextComponent> tooltip) {
        tooltip.add(CLOC.blank(""));
    }

    public static List<String> compsToStrings(List<ITextComponent> list) {
        return list.stream()
            .map(ITextComponent::getFormattedText)
            .collect(Collectors.toList());
    }

    public static ITextComponent level(int lvl) {
        return new StringTextComponent(TextFormatting.YELLOW + "").appendSibling(Words.Level.locName())
            .appendText((": " + lvl));

    }

    public static List<ITextComponent> cutIfTooLong(ITextComponent comp) {
        List<String> stringList = cutIfTooLong(CLOC.translate(comp));
        return stringList.stream()
            .map(x -> new SText(x))
            .collect(Collectors.toList());

    }

    public static List<String> cutIfTooLong(String str) {

        List<String> list = new ArrayList<>();

        int start = 0;
        int i = 0;
        for (Character c : str.toCharArray()) {

            if (i == str.length() - 1) {
                list.add(str.substring(start));
            } else if (i - start > 28 && c == ' ') {
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

    public static ITextComponent itemBrokenText(ItemStack stack, ICommonDataItem data) {

        if (data != null) {

            if (RepairUtils.isItemBroken(stack)) {
                ITextComponent comp = new StringTextComponent(X + " ").appendSibling(Words.Broken.locName());
                return comp;
            }

        }

        return null;
    }

    public static ITextComponent requirement(ITextComponent text, int stat, int req) {

        ITextComponent comp;

        if (stat >= req) {
            comp = new StringTextComponent(CHECKMARK);
        } else {
            comp = new StringTextComponent(X);
        }

        return comp.appendSibling(
            new StringTextComponent(TextFormatting.GRAY + " ").appendSibling(text)
                .appendText(": " + req));

    }

    public static List<ITextComponent> removeDoubleBlankLines(List<ITextComponent> list) {
        return removeDoubleBlankLines(list, 5000);
    }

    public static List<ITextComponent> removeDoubleBlankLines(List<ITextComponent> list, int minLinesCutAllBlanks) {

        List<ITextComponent> newt = new ArrayList();

        boolean lastIsEmpty = false;

        boolean alwaysRemoveEmpty = list.size() > minLinesCutAllBlanks;

        for (int i = 0; i < list.size(); i++) {

            if (list.get(i)
                .getFormattedText()
                .length() > 2) {
                lastIsEmpty = false;
                newt.add(list.get(i));
            } else {

                if ((lastIsEmpty || alwaysRemoveEmpty)) {

                } else {
                    newt.add(list.get(i));
                }

                lastIsEmpty = true;

            }
        }

        list.clear();

        list.addAll(newt);

        return newt;
    }

    public static ITextComponent rarity(Rarity rarity) {
        return (new StringTextComponent(rarity.textFormatting() + "")
            .appendSibling(rarity.locName()
                .appendText(" ")
                .appendSibling(Words.Item.locName())));
    }

    public static ITextComponent rarityShort(Rarity rarity) {
        return (new StringTextComponent(rarity.textFormatting() + "").appendSibling(rarity.locName()));
    }

    public static ITextComponent tier(int tier) {

        return Styles.YELLOWCOMP()
            .appendSibling(Words.Tier.locName())
            .appendText(": " + tier);

    }

    public static ITextComponent uniqueTier(int tier) {
        return Styles.YELLOWCOMP()
            .appendSibling(Words.Tier.locName())
            .appendText(" " + tier + " ")
            .appendSibling(UniqueGear.getInstance()
                .locName());

    }

    public static void abilityLevel(List<ITextComponent> list, int current, int max) {
        list.add(
            new SText(TextFormatting.YELLOW + "").appendText("Ability ")
                .appendSibling(Words.Level.locName())
                .appendText(": " + current + "/" + max));
    }
}
