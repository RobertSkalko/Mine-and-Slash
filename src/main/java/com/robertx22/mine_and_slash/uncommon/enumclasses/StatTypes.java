package com.robertx22.mine_and_slash.uncommon.enumclasses;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public enum StatTypes {

    Flat() {

    },
    Percent(),
    Multi();

    StatTypes() {

    }

    public static ITextComponent getSuffix(StatMod mod) {

        ITextComponent text = new StringTextComponent("");

        if (mod.Type() == StatTypes.Flat) {

            if (mod.GetBaseStat().IsPercent()) {
                text.appendText("%");
            }

        } else if (mod.Type() == StatTypes.Percent) {
            text.appendText("%");

        } else {
            text.appendText("% ").appendSibling(Words.Multi.locName());
        }
        return text;
    }

}
