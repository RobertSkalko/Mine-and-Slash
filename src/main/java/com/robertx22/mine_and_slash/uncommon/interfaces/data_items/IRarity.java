package com.robertx22.mine_and_slash.uncommon.interfaces.data_items;

import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;

public interface IRarity<R extends Rarity> {

    public int getRarityRank();

    public R getRarity();

    public default boolean isUnique() {
        return this.getRarityRank() == -1;
    }

    int Unique = -1;
    int Common = 0;
    int Uncommon = 1;
    int Rare = 2;
    int Epic = 3;
    int Legendary = 4;
    int Mythic = 5;
}
