package com.robertx22.mine_and_slash.uncommon.enumclasses;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

public enum StatModTypes {

    Flat("flat"),
    LOCAL_INCREASE("local_increase"),
    GLOBAL_INCREASE("global_increase");

    StatModTypes(String id) {
        this.id = id;

    }

    public String id;

    public boolean isFlat() {
        return this == Flat;
    }

    public static ITextComponent getNumberSuffix(StatModTypes type, Stat stat) {

        ITextComponent text = new StringTextComponent("");

        if (type == StatModTypes.Flat) {

            if (stat.IsPercent()) {
                text.appendText("%");
            }

        } else if (type == StatModTypes.LOCAL_INCREASE) {
            text.appendText("%");

        } else {
            text.appendText("%");
        }
        return text;
    }

    public static ITextComponent getSuffix(StatModTypes type) {

        ITextComponent text = new StringTextComponent("");

        if (type == StatModTypes.GLOBAL_INCREASE) {
            text.appendText(TextFormatting.GRAY + " ")
                .appendSibling(Words.Multi.locName());
        }

        return text;
    }

}
