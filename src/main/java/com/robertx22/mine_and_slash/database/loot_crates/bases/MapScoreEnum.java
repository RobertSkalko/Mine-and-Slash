package com.robertx22.mine_and_slash.database.loot_crates.bases;

import com.robertx22.mine_and_slash.uncommon.capability.player.PlayerMapCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import net.minecraft.entity.player.PlayerEntity;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

public enum MapScoreEnum {

    BAD(1, 0.25F, Words.Bad, 1F),
    AVERAGE(2, 0.5F, Words.Average, 0.9F),
    GOOD(3, 0.7F, Words.Good, 0.8F),
    GREAT(4, 0.85F, Words.Great, 0.5F),
    AMAZING(5, 1F, Words.Amazing, 0.4F);

    MapScoreEnum(int number, float itemRewardMulti, Words word, float timeNeeded) {
        this.number = number;
        this.itemRewardMulti = itemRewardMulti;
        this.word = word;
        this.timeNeeded = timeNeeded;
    }

    static String SCORE_SYMBOL = "\u2764";

    public static MapScoreEnum byNumber(int score) {
        return Arrays.asList(MapScoreEnum.values())
            .stream()
            .filter(x -> ((MapScoreEnum) x).number == score)
            .findFirst()
            .get();
    }

    public static MapScoreEnum getScore(PlayerEntity player) {

        PlayerMapCap.IPlayerMapData mapdata = Load.playerMapData(player);

        MapScoreEnum best = Arrays.stream(MapScoreEnum.values())
            .filter(x -> x.meetsTimeRequirement(mapdata))
            .collect(Collectors.toList())
            .stream()
            .max(Comparator.comparing(v -> v.number))
            .get();

        return best;
    }

    public String getTooltipLine() {
        String text = "";

        for (int i = 0; i < number; i++) {
            text += " " + SCORE_SYMBOL;
        }
        return text;
    }

    public boolean meetsTimeRequirement(PlayerMapCap.IPlayerMapData data) {

        return true;

        /*
        float percentTimeItTook = (float) data.getMinutesPassed() / (float) data.getMap().minutes;

        return timeNeeded >= percentTimeItTook;

         */

    }

    public float timeNeeded = 0;
    public float itemRewardMulti = 0;
    public int number = 0;
    public Words word;
}
