package com.robertx22.mine_and_slash.new_content.registry;

import com.robertx22.mine_and_slash.new_content.data_processors.*;

import java.util.ArrayList;
import java.util.List;

public class DataProcessors {

    static List<DataProcessor> all = new ArrayList<>();

    public static List<DataProcessor> getAll() {

        if (all.isEmpty()) {
            all.add(new BossProcessor());
            all.add(new EliteProcessor());
            all.add(new MobProcessor());
            all.add(new ChestProcessor());
            all.add(new MobHordeProcessor());
            all.add(new EliteMobHorde());
            all.add(new PuzzleProcessor());
            all.add(new ChanceChestProcessor());
            all.add(new RemoveAllBesidesOneProcessor());
            all.add(new TraderProcessor());
        }

        return all;

    }

}
