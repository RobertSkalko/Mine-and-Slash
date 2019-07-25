package com.robertx22.mine_and_slash.uncommon.comparators;

import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;

import java.util.Comparator;

public class RarityComparator implements Comparator<IRarity> {

    @Override
    public int compare(IRarity o1, IRarity o2) {

        int first = o1.getRarityRank();
        int second = o2.getRarityRank();

        if (first == second) {
            return 0;
        }
        return first > second ? 1 : -1;
    }
}
