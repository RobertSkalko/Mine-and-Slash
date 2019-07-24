package com.robertx22.mine_and_slash.database.rarities;

import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;

import java.util.ArrayList;
import java.util.List;

public abstract class RaritiesContainer<RarityType extends Rarity> {

    public static final int minRarity = 0;
    public static final int maxRarity = 5;

    public abstract List<RarityType> rarities();

    public List<RarityType> getRarities() {
        return new ArrayList<>(rarities());
    }

    public RarityType random() {
        return RandomUtils.weightedRandom(rarities());
    }

    public RarityType get(int i) {

        if (i < minRarity) {
            try {
                throw new Exception("Rarity can't be less than " + minRarity);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (i > maxRarity) {
            try {
                throw new Exception("Rarity can't be more than " + maxRarity);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return rarities().get(i);

    }

}
