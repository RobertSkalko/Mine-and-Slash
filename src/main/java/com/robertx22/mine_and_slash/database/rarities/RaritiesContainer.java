package com.robertx22.mine_and_slash.database.rarities;

import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public abstract class RaritiesContainer<RarityType extends Rarity> {

    public int minRarity;
    public int maxRarity;

    public RaritiesContainer() {

    }

    public final void onInit() {
        this.minRarity = rarities().stream().min((Comparator.comparingInt(Rarity::Rank))).get().Rank();
        this.maxRarity = rarities().stream().max((Comparator.comparingInt(Rarity::Rank))).get().Rank();

    }

    HashMap<Integer, RarityType> map = new HashMap<>();

    public final HashMap<Integer, RarityType> getMap() {
        return map;
    }

    public List<RarityType> rarities() {
        return new ArrayList<>(getMap().values());
    }

    protected void add(RarityType r) {
        this.getMap().put(r.Rank(), r);
    }

    public List<RarityType> getRarities() {
        return new ArrayList<>(rarities());
    }

    public RarityType random() {
        return RandomUtils.weightedRandom(rarities());
    }

    public final RarityType get(int i) {

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

        return getMap().get(i);

    }

}
