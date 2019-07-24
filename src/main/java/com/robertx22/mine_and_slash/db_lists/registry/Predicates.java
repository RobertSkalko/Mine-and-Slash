package com.robertx22.mine_and_slash.db_lists.registry;

import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.ITiered;

import java.util.function.Predicate;

public class Predicates<T extends ISlashRegistryEntry> {

    public Predicate<T> ofRarityOrHigher(int rarity) {
        return x -> x instanceof IRarity && ((IRarity) x).getRarityRank() >= rarity;
    }

    public Predicate<T> ofSpecificRarity(int rarity) {
        return x -> x instanceof IRarity && ((IRarity) x).getRarityRank() == rarity;
    }

    public Predicate<T> ofTierOrLess(int rarity) {
        return x -> x instanceof ITiered && ((ITiered) x).Tier() <= rarity;
    }

}
