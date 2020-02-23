package com.robertx22.mine_and_slash.new_content.registry;

import com.robertx22.mine_and_slash.new_content.enums.RoomType;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;

import java.util.List;
import java.util.Random;

public class RoomsList {

    public static RoomType randomRoomType(List<RoomType> list, Random rand) {
        return RandomUtils.weightedRandom(list, rand.nextDouble());
    }

}