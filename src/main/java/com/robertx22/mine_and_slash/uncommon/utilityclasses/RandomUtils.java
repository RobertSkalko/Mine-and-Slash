package com.robertx22.mine_and_slash.uncommon.utilityclasses;

import com.robertx22.mine_and_slash.uncommon.interfaces.IWeighted;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class RandomUtils {

    private static Random ran = new Random();

    public static int RandomRange(int min, int max) {
        // prevents trying to nextin on 0
        if (min == max) {
            return min;
        }

        int result = ran.nextInt(max - min);

        return result + min;

    }

    private static <OLD, NEW> List<NEW> CollectionToList(Collection<OLD> coll) {

        List<NEW> list = new ArrayList<NEW>();

        for (OLD old : coll) {
            list.add((NEW) old);
        }

        return list;

    }

    public static boolean roll(int chance) {

        double ranNum = ran.nextDouble() * 100;

        if (chance > ranNum) {
            return true;
        }

        return false;
    }

    public static boolean roll(float chance) {

        double ranNum = ran.nextDouble() * 100;

        if (chance > ranNum) {
            return true;
        }

        return false;
    }

    public static <T extends IWeighted> T weightedRandom(Collection<T> coll) {

        if (coll == null || coll.isEmpty()) {
            return null;
        }
        return (T) WeightedRandom(CollectionToList(coll));

    }

    private static IWeighted WeightedRandom(List<IWeighted> lootTable) {

        double value = Total(lootTable) * Math.random();
        double weight = 0;

        for (IWeighted item : lootTable) {
            weight += item.Weight();
            if (value < weight)
                return item;
        }

        return null;

    }

    private static int Total(List<IWeighted> list) {

        int total = 0;

        for (IWeighted w : list) {
            total += w.Weight();
        }
        return total;

    }

}
