package com.robertx22.mine_and_slash.new_content.registry;

import com.robertx22.mine_and_slash.new_content.data_processors.BossProcessor;
import com.robertx22.mine_and_slash.new_content.data_processors.EliteProcessor;
import com.robertx22.mine_and_slash.new_content.data_processors.MobProcessor;

import java.util.ArrayList;
import java.util.List;

public class DataProcessors {

    public static List<DataProcessor> getAll() {

        List<DataProcessor> list = new ArrayList<>();
        list.add(new BossProcessor());
        list.add(new EliteProcessor());
        list.add(new MobProcessor());

        return list;

    }

}
