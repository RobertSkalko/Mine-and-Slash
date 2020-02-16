package com.robertx22.mine_and_slash.database.talent_tree;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.saveclasses.ExactStatData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;

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

    public static PerkEffect trait(String guid, Stat stat, PerkType type) {

        ExactStatData data = new ExactStatData(100, StatModTypes.Flat, stat.GUID());

        return new PerkEffect(guid, data, stat).type(type);

    }

    public static PerkEffectsWrapper build(String guid, Stat stat, float num,
                                           StatModTypes type) {
        ExactStatData statdata = new ExactStatData(num, type, stat);
        return build(guid, stat, statdata);

    }

    public static PerkEffectsWrapper build(String guid, Stat stat, ExactStatData data) {
        HashMap<PerkType, PerkEffect> map = new HashMap<>();

        for (PerkType type : PerkType.values()) {

            ExactStatData data2 = new ExactStatData(data);
            data2.setValue(data2.getValue() * type.statMulti);

            String id = guid + "_" + type.name().toLowerCase();

            PerkEffect neww = new PerkEffect(id, data2, stat).type(type);

            map.put(type, neww);

        }
        return new PerkEffectsWrapper(map);
    }

    public static PerkEffectsWrapper build(String guid, List<ExactStatData> list) {
        HashMap<PerkType, PerkEffect> map = new HashMap<>();

        for (PerkType type : PerkType.values()) {

            List<ExactStatData> stats = new ArrayList<>();

            for (ExactStatData x : list) {
                ExactStatData stat = new ExactStatData(x);
                stat.setValue(stat.getValue() * type.statMulti);
                stats.add(stat);
            }
            String id = guid + "_" + type.name().toLowerCase();

            PerkEffect neww = new PerkEffect(id, stats, guid).type(type);

            map.put(type, neww);

        }
        return new PerkEffectsWrapper(map);
    }

}
