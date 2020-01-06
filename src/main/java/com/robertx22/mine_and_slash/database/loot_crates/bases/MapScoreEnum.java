package com.robertx22.mine_and_slash.database.loot_crates.bases;

import com.robertx22.mine_and_slash.uncommon.localization.Words;

public enum MapScoreEnum {

    BAD(1, 0.25F, Words.Bad),
    AVERAGE(2, 0.5F, Words.Average),
    GOOD(3, 0.7F, Words.Good),
    GREAT(4, 0.85F, Words.Great),
    AMAZING(5, 1F, Words.Amazing);

    MapScoreEnum(int number, float itemRewardMulti, Words word) {
        this.number = number;
        this.itemRewardMulti = itemRewardMulti;
        this.word = word;
    }
    
    static String SCORE_SYMBOL = "\u2764";

    public String getTooltipLine() {
        String text = "";

        for (int i = 0; i < number; i++) {
            text += " " + SCORE_SYMBOL;
        }
        return text;
    }

    float itemRewardMulti = 0;
    int number = 0;
    Words word;
}
