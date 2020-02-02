package com.robertx22.mine_and_slash.config.lvl_penalty;

import com.robertx22.mine_and_slash.db_lists.registry.ISlashRegistryInit;

import java.util.Comparator;
import java.util.HashMap;

public class LvlPenaltyContainer implements ISlashRegistryInit {

    public static LvlPenaltyContainer INSTANCE = new LvlPenaltyContainer();

    private String Explanation1 = "This determines the loot and exp multiplier when killing mobs.";
    private String Explanation2 = "(-5,1) means if you're close to 5 lvls lower, multiply by 1," + " meaning do " +
            "nothing.";
    private String Explanation3 = "(20,0.5) means if you're close to 20 lvls higher, reduce loot by 50%,";
    private String Explanation4 = "Number is picked by getting the closest number to the lvl difference";
    private String Explanation6 = " Level Difference = playerLVL - mobLVL";

    public static LvlPenaltyContainer defaultPenalty() {

        LvlPenaltyContainer c = new LvlPenaltyContainer();

        c.map.put(-100, 0.5D);
        c.map.put(-20, 0.8D);
        c.map.put(-10, 0.9D);
        c.map.put(-5, 1D);
        c.map.put(0, 1D);
        c.map.put(6, 0.8D);
        c.map.put(10, 0.7D);
        c.map.put(16, 0.5D);
        c.map.put(31, 0.05D);
        c.map.put(51, 0.025D);
        c.map.put(100, 0.01D);

        return c;

    }

    private HashMap<Integer, Double> map = new HashMap<>();

    public transient HashMap<Integer, Double> alreadyCalcMap = new HashMap<>();

    public Double getMultiForLevelDifference(int playerLvl, int mobLvl) {

        int diff = playerLvl - mobLvl;

        if (!alreadyCalcMap.containsKey(diff)) {

            Double bestMatch = this.map.entrySet()
                    .stream()
                    .min(Comparator.comparingInt(i -> Math.abs(i.getKey() - diff)))
                    .get()
                    .getValue();

            alreadyCalcMap.put(diff, bestMatch);

        }

        return alreadyCalcMap.get(diff);

    }

    @Override
    public void registerAll() {
        INSTANCE = this;
    }

}