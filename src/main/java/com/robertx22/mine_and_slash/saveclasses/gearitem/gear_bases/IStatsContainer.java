package com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases;

import com.robertx22.mine_and_slash.saveclasses.gearitem.StatModData;

import java.util.ArrayList;
import java.util.List;

public interface IStatsContainer {

    static class LevelAndStats {

        public LevelAndStats(List<StatModData> mods, int level) {
            this.level = level;
            this.mods = mods;
        }

        public int level = 1;

        public List<StatModData> mods = new ArrayList();
/*
        public static List<LevelAndStats> mergeDuplicates(
                List<LevelAndStats> duplicates) {

            List<LevelAndStats> list = new ArrayList<>();

            for (LevelAndStats lvlstats : duplicates) {

                if (list.contains())

            }

            return list;

        }

 */

    }

    public List<LevelAndStats> GetAllStats(int level);
}
