package com.robertx22.mine_and_slash.database.rarities;

import com.google.common.base.Preconditions;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseRaritiesContainer<T extends Rarity> {

    public int minRarity;
    public int maxRarity;

    public List<T> normalRarities;

    HashMap<Integer, T> map = new HashMap<>();

    public BaseRaritiesContainer() {

    }

    public void updateFromDatapack(List<T> rarities) {

        Preconditions.checkArgument(rarities.size() == map.size(), "Rarities can't be added or removed through datapacks. This is a hard limitation.");

        map = new HashMap<>();
        rarities.forEach(x -> map.put(x.Rank(), x));
        onInit();
    }

    public final void onInit() {
        this.minRarity = getAllRarities().stream()
            .min((Comparator.comparingInt(Rarity::Rank)))
            .get()
            .Rank();
        this.maxRarity = getAllRarities().stream()
            .max((Comparator.comparingInt(Rarity::Rank)))
            .get()
            .Rank();

        normalRarities = getMap().values()
            .stream()
            .filter(x -> x.Rank() >= IRarity.Common && x.Rank() <= IRarity.Highest)
            .collect(Collectors.toList());

    }

    public final HashMap<Integer, T> getMap() {
        return map;
    }

    public List<T> getNormalRarities() {
        return new ArrayList<>(normalRarities);
    }

    protected void add(T r) {
        this.getMap()
            .put(r.Rank(), r);
    }

    public List<T> getAllRarities() {
        return new ArrayList<>(getMap().values());
    }

    public T random() {
        return RandomUtils.weightedRandom(getNormalRarities());
    }

    public final T get(int i) {

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

    public abstract RarityTypeEnum getType();
}
