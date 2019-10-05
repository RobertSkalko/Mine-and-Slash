package com.robertx22.mine_and_slash.new_content_test.talent_tree;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.saveclasses.ExactStatData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PerkEffectBuilder {

    public static PerkEffectsWrapper build(String id, PerkEffect... effects) {
        List<ExactStatData> stats = new ArrayList<>();
        for (PerkEffect effect : effects) {
            stats.addAll(effect.exactStats);
        }
        return build(id, stats);
    }

    public static PerkEffectsWrapper build(Stat stat, ExactStatData data) {
        HashMap<PerkType, PerkEffect> map = new HashMap<>();

        for (PerkType type : PerkType.values()) {

            ExactStatData data2 = new ExactStatData(data);
            data2.setValue(data2.getValue() * type.statMulti);

            PerkEffect neww = new PerkEffect(data2, stat).type(type);

            map.put(type, neww);

        }
        return new PerkEffectsWrapper(map);
    }

    public static PerkEffectsWrapper build(String id, List<ExactStatData> list) {
        HashMap<PerkType, PerkEffect> map = new HashMap<>();

        for (PerkType type : PerkType.values()) {

            List<ExactStatData> stats = new ArrayList<>();

            for (ExactStatData x : list) {
                ExactStatData stat = new ExactStatData(x);
                stat.setValue(stat.getValue() * type.statMulti);
                stats.add(stat);
            }

            PerkEffect neww = new PerkEffect(stats, id).type(type);

            map.put(type, neww);

        }
        return new PerkEffectsWrapper(map);
    }

}
