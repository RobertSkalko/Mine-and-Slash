package com.robertx22.mine_and_slash.new_content_test.talent_tree;

import com.robertx22.mine_and_slash.saveclasses.ExactStatData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class PerkEffectBuilder {

    public static PerkEffectsWrapper build(ExactStatData stat) {
        return build(Arrays.asList(stat));
    }

    public static PerkEffectsWrapper build(List<ExactStatData> list) {
        HashMap<PerkType, PerkEffect> map = new HashMap<>();

        for (PerkType type : PerkType.values()) {

            List<ExactStatData> stats = new ArrayList<>();

            for (ExactStatData x : list) {
                ExactStatData stat = new ExactStatData(x);
                stat.setValue(stat.getValue() * type.statMulti);
                stats.add(stat);
            }

            PerkEffect neww = new PerkEffect(stats).type(type);

            map.put(type, neww);

        }
        return new PerkEffectsWrapper(map);
    }

}
