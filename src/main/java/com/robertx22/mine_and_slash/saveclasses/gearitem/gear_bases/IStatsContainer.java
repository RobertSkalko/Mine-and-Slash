package com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases;

import com.robertx22.mine_and_slash.saveclasses.gearitem.StatModData;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface IStatsContainer {

    static class LevelAndStats {

        public LevelAndStats(List<StatModData> mods, int level) {
            this.level = level;
            this.mods = mods;
        }

        public int level = 1;

        public List<StatModData> mods = new ArrayList();

        public static List<LevelAndStats> mergeDuplicates(
                List<LevelAndStats> duplicates) {

            List<LevelAndStats> list = new ArrayList<>();

            for (LevelAndStats duplicate : duplicates) {

                Optional<LevelAndStats> found = list.stream()
                        .filter(x -> x.level == duplicate.level)
                        .findFirst();

                if (found.isPresent()) {
                    found.get().mods.addAll(duplicate.mods);

                } else {
                    list.add(duplicate);
                }
            }

            for (LevelAndStats merged : list) {

                List<StatModData> mergedMods = new ArrayList<>();

                for (StatModData mod : new ArrayList<>(merged.mods)) {

                    if (mergedMods.stream().anyMatch(x -> x.canBeMerged(mod))) {
                        StatModData mergedMod = mergedMods.stream()
                                .filter(x -> x.canBeMerged(mod))
                                .findFirst()
                                .get();
                        mergedMod.setPercent(mergedMod.getPercent() + mod.getPercent());

                    } else {
                        mergedMods.add(mod);
                    }

                }
                merged.mods = mergedMods;
            }

            return list;

        }

    }

    public List<LevelAndStats> GetAllStats(int level);
}
