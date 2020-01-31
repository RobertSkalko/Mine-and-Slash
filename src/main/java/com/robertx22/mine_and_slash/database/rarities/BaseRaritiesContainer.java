package com.robertx22.mine_and_slash.database.rarities;

import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseRaritiesContainer<RarityType extends Rarity> {

    public int minRarity;
    public int maxRarity;

    public List<RarityType> normalRarities;

    HashMap<Integer, RarityType> map = new HashMap<>();

    public BaseRaritiesContainer() {

    }

    public final void onInit() {
        this.minRarity = getAllRarities().stream().min((Comparator.comparingInt(Rarity::Rank))).get().Rank();
        this.maxRarity = getAllRarities().stream().max((Comparator.comparingInt(Rarity::Rank))).get().Rank();

        normalRarities = getMap().values()
                .stream()
                .filter(x -> x.Rank() >= IRarity.Common && x.Rank() <= IRarity.Mythic)
                .collect(Collectors.toList());

    }

    public final HashMap<Integer, RarityType> getMap() {
        return map;
    }

    public List<RarityType> getNormalRarities() {
        return normalRarities;
    }

    protected void add(RarityType r) {
        this.getMap().put(r.Rank(), r);
    }

    public List<RarityType> getAllRarities() {
        return new ArrayList<>(getMap().values());
    }

    public RarityType random() {
        return RandomUtils.weightedRandom(getNormalRarities());
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
