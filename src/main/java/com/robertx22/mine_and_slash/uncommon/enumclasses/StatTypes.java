package com.robertx22.mine_and_slash.uncommon.enumclasses;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

public enum StatTypes {

    Flat("flat") {
    },
    Percent("percent"),
    Multi("multi");

    StatTypes(String id) {
        this.id = id;
    }

    public String id;

    public static ITextComponent getNumberSuffix(StatTypes type, Stat stat) {

        ITextComponent text = new StringTextComponent("");

        if (type == StatTypes.Flat) {

            if (stat.IsPercent()) {
                text.appendText("%");
            }

        } else if (type == StatTypes.Percent) {
            text.appendText("%");

        } else {
            text.appendText("%");
        }
        return text;
    }

    public static ITextComponent getSuffix(StatTypes type) {

        ITextComponent text = new StringTextComponent("");

        if (type == StatTypes.Multi) {
            text.appendText(TextFormatting.GRAY + " ").appendSibling(Words.Multi.locName());
        }

        return text;
    }

}
